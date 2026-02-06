package brandy.newcld.pokemon.ui.detail

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.FloatExponentialDecaySpec
import androidx.compose.animation.core.animateDecay
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import brandy.newcld.pokemon.ui.R
import brandy.newcld.pokemon.ui.theme.Secondary
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

private val MinAppBarHeight = 32.dp
private val MaxAppBarHeight = 88.dp

@Composable
private fun rememberAppBarState(toolbarHeightRange: IntRange): AppBarState {
    return rememberSaveable(saver = ExitUntilCollapsedState.Saver) {
        ExitUntilCollapsedState(toolbarHeightRange)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailScreen(
    modifier: Modifier = Modifier,
    pid: Int? = 1,
    onClickBack: () -> Unit,
) {
//    val density = LocalDensity.current
//    val appBarHeightRange = with(density) {
//        MinAppBarHeight.roundToPx()..MaxAppBarHeight.roundToPx()
//    }
//    val appBarState = rememberAppBarState(appBarHeightRange)
//    val listState = rememberLazyListState()
//
//    val scope = rememberCoroutineScope()
//
//    val nestedScrollConnection = remember {
//        object : NestedScrollConnection {
//            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
//                appBarState.scrollTopLimitReached = listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset == 0
//                appBarState.scrollOffset -= available.y
//                return Offset(0f, appBarState.consumed)
//            }
//
//            override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
//                if (available.y > 0) {
//                    scope.launch {
//                        animateDecay(
//                            initialValue = appBarState.height + appBarState.offset,
//                            initialVelocity = available.y,
//                            animationSpec = FloatExponentialDecaySpec()
//                        ) { value, velocity ->
//                            appBarState.scrollTopLimitReached = listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset == 0
//                            appBarState.scrollOffset -= (value - (appBarState.height + appBarState.offset))
//                            if (appBarState.scrollOffset == 0f) scope.coroutineContext.cancelChildren()
//                        }
//                    }
//                }
//
//                return super.onPostFling(consumed, available)
//            }
//        }
//    }

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val f = scrollBehavior.state.collapsedFraction
    var isCollapsed by remember { mutableStateOf(false) }

    LaunchedEffect(f) {
        isCollapsed = when {
            !isCollapsed && f > 0.98f -> true
            isCollapsed && f <= 0.85f -> false
            else -> isCollapsed
        }
    }


    Column(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ){
        MediumTopAppBar(
            scrollBehavior = scrollBehavior,
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White,
                titleContentColor = Color.White
            ),
            title = {
                if(isCollapsed) {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // 앱바 아이콘
                        AsyncImage(
                            model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pid}.png",
                            contentDescription = null,
                            modifier = modifier.size(56.dp)
                        )

                        Text("영어 이름",
                            style = TextStyle(color = Color.DarkGray),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis)
                    }
                }
            },
            navigationIcon = {
                // 뒤로가기 버튼
                IconButton(
                    onClick = { onClickBack() }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_back_round),
                        contentDescription = "back button icon",
                        modifier = Modifier.size(16.dp),
                        tint = Secondary
                    )
                }
            },
            actions = {
                // 속성 아이콘
                AsyncImage(
                    model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/types/generation-viii/sword-shield/18.png",
                    contentDescription = null,
                    modifier = modifier.size(56.dp)
                )
            }
        )
        LazyColumn(
//            contentPadding = PaddingValues(
//                top = TopAppBarDefaults.LargeAppBarCollapsedHeight,
//            )
        ) {
            item {
                Box {
                    Row(
                        modifier = modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${pid}.png",)
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = modifier
                                .size(200.dp)
                                .alpha(alpha = 1 - f * f),
                            alignment = Alignment.BottomCenter
                        )
                        Text(
                            "영어 이름",
                            style = TextStyle(color = Color.DarkGray),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
            item {
                Text("상세 내용 id=$pid", Modifier.padding(horizontal = 16.dp))
                Spacer(Modifier.height(1200.dp)) // 테스트용
            }

        }
        }
//        LazyColumn(
//            state = listState,
//            modifier = modifier
//                .fillMaxSize()
//                .graphicsLayer { translationY = appBarState.height + appBarState.offset },
//        ) {
//            item {
//                Spacer(Modifier.height(16.dp))
//                Text("상세 내용 id=$pid", Modifier.padding(horizontal = 16.dp))
//                Spacer(Modifier.height(1200.dp)) // 테스트용
//            }
//        }
//        CollapsingAppBar(
//            pid = pid,
//            boxModifier = modifier
//                .fillMaxWidth()
//                .height(with(LocalDensity.current) { appBarState.height.dp })
//                .graphicsLayer { translationY = appBarState.offset },
//            progress = appBarState.progress,
//            onClickBack = onClickBack
//        )


}