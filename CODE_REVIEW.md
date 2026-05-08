# Android 코드 리뷰

> 리뷰 날짜: 2026-04-17  
> 리뷰어: Claude (Android 10년차 관점)

---

## 🔴 HIGH — 즉시 수정 권장

### 1. `PaletteUtil.kt` — ImageLoader를 매 호출마다 새로 생성

`paletteBackgroundColor()`가 호출될 때마다 `ImageLoader(context)`를 새로 생성하고 있음.  
`ImageLoader`는 내부에 캐시·스레드풀을 들고 있는 무거운 객체라 매번 만들면 메모리 낭비와 성능 저하가 발생함.

```kotlin
// ❌ 현재
val loader = ImageLoader(context)

// ✅ 수정: Singleton으로 관리
object PaletteUtil {
    private var imageLoader: ImageLoader? = null

    private fun getImageLoader(context: Context): ImageLoader {
        return imageLoader ?: ImageLoader(context.applicationContext).also { imageLoader = it }
    }
}
```

> Hilt로 `ImageLoader`를 싱글톤 주입하는 방식도 고려 가능.

---

### 2. `PokemonListScreen.kt` — 리컴포지션마다 Log 출력

```kotlin
// ❌ 현재: 아이템마다 실행 → 목록이 많을수록 로그 폭탄
items(count = items.itemCount) { index ->
    Log.d(TAG, "PokemonListScreen: ${localItems.toString()}") // 삭제 필요
}
```

LazyGrid 아이템 콜백 안에서 `Log`를 찍으면 스크롤 시 수백 번 호출됨. 완전히 제거 필요.

---

### 3. `PokemonListScreen.kt` — 인덱스 기반 이중 페이징 동기화

```kotlin
// ❌ 현재: remote index == local index라는 가정
val pokemonLocal = localItems[index]
```

`items`(remote paging)와 `localItems`(local paging)는 별개의 PagingSource.  
각자의 로딩 타이밍이 다르기 때문에 인덱스가 틀어지면 A포켓몬 이미지에 B포켓몬 이름이 붙는 버그 발생 가능.

```kotlin
// ✅ 수정: id를 기준으로 Map 조회
val pokemonLocalMap by pokemonListViewModel.pokemonLocalMap.collectAsState()

items(count = items.itemCount, key = { items.peek(it)?.id ?: it }) { index ->
    val pokemon = items[index] ?: return@items
    val pokemonLocal = pokemonLocalMap[pokemon.id] ?: return@items
    PokemonListItem(...)
}
```

ViewModel에서 `Map<Int, PokemonItemLocalModel>`로 변환해 id로 조회하는 방식이 안전함.

---

### 4. `PokemonDetailScreen.kt` — 실제 데이터 미연결

```kotlin
// ❌ 현재: ViewModel 호출이 주석 처리
LaunchedEffect(Unit) {
//    pokemonDetailViewModel.getPokemonInfo(pid = pid)
}

// 이름도 전부 하드코딩
name = "이름"
koName = "이름"
typeImageUrl = "https://.../18.png"   // 항상 노말 타입 고정
Spacer(Modifier.height(1200.dp))     // 테스트용 스페이서 → 제거
```

ViewModel에 데이터는 준비됐는데 UI가 연결되지 않은 상태. 현재 가장 큰 미완성 영역.

---

## 🟡 MEDIUM — 개선 권장

### 5. `AppNavHost.kt` — 라우트 문자열 하드코딩

```kotlin
// ❌ 현재: 문자열 직접 사용 → 오타 시 런타임 크래시
navController.navigate("pokemon_detail/${pid}")
route = "pokemon_detail/{pid}"
```

```kotlin
// ✅ 수정: sealed class로 타입 안전하게
sealed class Screen(val route: String) {
    object List : Screen("pokemon_list")
    data class Detail(val pid: Int) : Screen("pokemon_detail/$pid") {
        companion object { const val ROUTE = "pokemon_detail/{pid}" }
    }
}
```

---

### 6. `PokemonListViewModel.kt` — 불필요한 `viewModelScope.launch` 중첩

```kotlin
// ❌ 현재: launch 안에서 bind() 호출 → 코루틴 중첩
fun onColorExtracted(pid: Int, baseColor: Int) {
    viewModelScope.launch {
        ...
        updateBackgroundColorsUseCase(...).bind(...)  // bind()가 이미 launchIn(viewModelScope)
    }
}
```

`bind()`는 내부에서 `launchIn(viewModelScope)`를 호출하므로 외부 `viewModelScope.launch`가 불필요.

```kotlin
// ✅ 수정
fun onColorExtracted(pid: Int, baseColor: Int) {
    val dayTimeColor = toPastelColor(baseColor)
    val nightTimeColor = toDarkerColor(baseColor)
    _bgColors.update { it + (pid to DayNight(dayTimeColor, nightTimeColor)) }
    updateBackgroundColorsUseCase(pid, dayTimeColor, nightTimeColor).bind(
        state = _updateState,
        mapper = { pid }
    )
}
```

---

### 7. `AppModule.kt` — Application 다운캐스팅

```kotlin
// ❌ 현재: 캐스트 실패 가능
fun provideBaseUrl(application: Application): String =
    (application as PokemonApplication).baseUrl

// ✅ 수정: 직접 상수로
@Provides @Named("baseUrl")
fun provideBaseUrl(): String = "https://pokeapi.co/api/v2/"
```

---

## 🟢 MINOR — 코드 품질

### 8. `MainActivity.kt` — 사용하지 않는 변수

```kotlin
// 선언만 하고 어디서도 사용하지 않음 → 삭제
val currentRoute = navBackStackEntry?.destination?.route
```

---

### 9. `PokemonListItem.kt` — `LocalContext.current` 중복 호출

```kotlin
val context = LocalContext.current        // 위에서 선언
model = ImageRequest.Builder(LocalContext.current)  // 다시 호출 → context 재사용
```

---

### 10. `PokemonListViewModel.kt` — `consumeUpdateResult()` 미사용 함수

`consumeUpdateResult()`는 UI에서 한 번도 호출되지 않고, `updateState`도 UI에서 collect하지 않음.  
현재 dead code → 제거하거나 UI에 연결 필요.

---

## 잘 된 부분

- `flowDataResource` + `bind()` 패턴으로 보일러플레이트를 잘 줄임
- `combineDataResource`로 두 Flow를 합치는 구조가 깔끔함
- `ScaleIndication`의 `IndicationNodeFactory` 구현이 Compose 최신 API를 잘 활용함
- Palette 색상을 day/night로 나눠 저장하는 설계가 좋음

---

## 우선순위 요약

| 순위 | 파일 | 이슈 |
|------|------|------|
| 1 | `PokemonListScreen.kt` | `Log.d` 즉시 제거 |
| 2 | `PaletteUtil.kt` | ImageLoader 싱글톤화 |
| 3 | `PokemonListScreen.kt` | 인덱스 기반 이중 페이징 → Map 기반으로 교체 |
| 4 | `PokemonDetailScreen.kt` | 하드코딩 제거 + ViewModel 연결 |
| 5 | `AppNavHost.kt` | 라우트 상수화 |
| 6 | `PokemonListViewModel.kt` | `viewModelScope.launch` 중첩 제거 |
| 7 | `AppModule.kt` | Application 캐스트 제거 |
| 8 | `MainActivity.kt` | 미사용 변수 제거 |
| 9 | `PokemonListItem.kt` | `LocalContext.current` 중복 제거 |
| 10 | `PokemonListViewModel.kt` | Dead code 정리 |
