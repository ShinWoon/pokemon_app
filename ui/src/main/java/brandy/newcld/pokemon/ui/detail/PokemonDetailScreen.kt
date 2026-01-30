package brandy.newcld.pokemon.ui.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailScreen(
    modifier: Modifier = Modifier,
    pid: Int? = 1,
    onCollapsedFraction: (Float) -> Unit,
    onExpandedTitleCoords: (LayoutCoordinates) -> Unit,
) {
    val listState = rememberLazyListState()
    val density = LocalDensity.current

    val maxHeight = 240.dp
    val minHeight = 0.dp
    val rangePx = with(density) { (maxHeight - minHeight).toPx() }.coerceAtLeast(1f)

    // 스크롤 접힘 정도 계산
    val collapsePx by remember {
        derivedStateOf {
            if(listState.firstVisibleItemIndex == 0) {
                listState.firstVisibleItemScrollOffset.toFloat().coerceIn(0f, rangePx)
            } else {
                rangePx
            }
        }
    }

    val fraction by remember { derivedStateOf { (collapsePx/rangePx).coerceIn(0f, 1f) } }

    // main에 fraction 전달
    LaunchedEffect(fraction) {
        onCollapsedFraction(fraction)
    }

    val headerHeight = maxHeight - with(density) { collapsePx.toDp() }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            state = listState,
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(top = maxHeight)
        ) {
            item {
                Spacer(Modifier.height(16.dp))
                Text("상세 내용 id=$pid", Modifier.padding(horizontal = 16.dp))
                Spacer(Modifier.height(1200.dp)) // 테스트용
            }
        }
        CollapsingAppBar(
            fraction = fraction,
            pid = pid,
            imageHeight = maxHeight,
            boxModifier = modifier
                .fillMaxWidth()
                .height(headerHeight)
                .clipToBounds(),
            onExpandedTitleCoords = onExpandedTitleCoords
        )

    }
}