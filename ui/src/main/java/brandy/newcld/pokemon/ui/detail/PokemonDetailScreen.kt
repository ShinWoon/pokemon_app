package brandy.newcld.pokemon.ui.detail

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailScreen(
    modifier: Modifier = Modifier,
    onProvideNestedScroll: (NestedScrollConnection?) -> Unit,
    onCollapsedFraction: (Float) -> Unit,
    pid: Int? = 1,
) {
    val density = LocalDensity.current
    val maxHeight = 260.dp
    val minHeight = 0.dp
    val maxHeightPx = with(density) { maxHeight.toPx() }
    val minHeightPx = with(density) { minHeight.toPx() }

    val rangePx = (maxHeightPx - minHeightPx).coerceAtLeast(1f)

    val appBarState = rememberTopAppBarState()
    LaunchedEffect(rangePx) {
        appBarState.heightOffsetLimit = -rangePx
    }

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        state = appBarState
    )
    val scrollState = rememberScrollState()

    // main과 nestedScroll 연결 (화면 들어올 때 설정, 나갈 때 해제)
    DisposableEffect(Unit) {
        onProvideNestedScroll(scrollBehavior.nestedScrollConnection)
        onDispose { onProvideNestedScroll(null) }
    }

    val snapAnim = remember { Animatable(0f) }
    // 접힘 겅도를 main으로 보냄
    LaunchedEffect(scrollState, scrollBehavior) {
        snapshotFlow { scrollState.isScrollInProgress }
            .collect { inProgress ->
                if (inProgress) {
                    // 사용자가 다시 드래그 시작하면 애니메이션 즉시 중단 → 버벅임 방지
                    if (snapAnim.isRunning) snapAnim.stop()
                } else {
                    // 스크롤이 완전히 멈춘 순간에만 “촥” 정렬
                    val offset = scrollBehavior.state.heightOffset // 0(펼침) ~ -range(접힘)
                    val collapse = (-offset / rangePx).coerceIn(0f, 1f)

                    // 원하는 임계값: 0.5~0.8 취향. (낮을수록 더 빨리 촥 접힘)
                    val target = if (collapse > 0.65f) -rangePx else 0f

                    if (kotlin.math.abs(offset - target) > 0.5f) {
                        snapAnim.snapTo(offset)
                        snapAnim.animateTo(
                            targetValue = target,
                            animationSpec = spring(
                                stiffness = Spring.StiffnessMediumLow,
                                dampingRatio = Spring.DampingRatioNoBouncy
                            )
                        ) {
                            scrollBehavior.state.heightOffset = value
                        }
                    }
                }
            }
    }

    // heightOffset: 0 (펼침) -> -rangePx (접힘)
    val heightOffsetPx by remember {
        derivedStateOf { scrollBehavior.state.heightOffset.coerceIn(-rangePx, 0f) }
    }
    val collapse by remember {
        derivedStateOf { (-heightOffsetPx / rangePx).coerceIn(0f, 1f) }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {
        CollapsingAppBar(
            collapse = collapse,
            pid = pid,
            boxModifier = modifier
                .fillMaxWidth()
                .height(maxHeight)
                .clipToBounds()
                .graphicsLayer { translationY = heightOffsetPx}
        )

        Column(
            modifier = modifier
                .fillMaxSize()
                .graphicsLayer { translationY = heightOffsetPx }
        ) {
            Spacer(Modifier.height(16.dp))
            Text("상세 내용 id=$pid", Modifier.padding(horizontal = 16.dp))
            Spacer(Modifier.height(1200.dp)) // 테스트용
        }
    }
}