package brandy.newcld.pokemon.ui.detail

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.sqrt

@Composable
fun HeaderTitleMoveToAppBarApprox(
    title: String,
    fraction: Float,
    endPadding: Dp = 16.dp,
    bottomPadding: Dp = 16.dp,

    navSlotWidth: Dp = 48.dp, // Icon button 터치 영역
    titleRowImage: Dp = 16.dp,
    titleRowGap: Dp = 4.dp,
    titleRowStartExtra: Dp = 0.dp,
    moveUp: Dp = 40.dp
) {
    val f = fraction.coerceIn(0f, 1f)

    val screenW = Configuration.SCREEN_WIDTH_DP_UNDEFINED.dp

    val targetX = navSlotWidth + titleRowImage + titleRowGap + titleRowStartExtra

    val moveX = -(screenW - targetX - endPadding)

    val tx = lerpDp(0.dp, moveX, f)
    val ty = lerpDp(0.dp, -moveUp, f)

    val alpha = 1f - sqrt(f)

    val density = LocalDensity.current
    Text(
        text = title,
        maxLines = 1,
        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
        modifier = androidx.compose.ui.Modifier
            .padding(end = endPadding, bottom = bottomPadding)
            .graphicsLayer {
                translationX = with(density) { tx.toPx() }
                translationY = with(density) { ty.toPx() }
                this.alpha = alpha
            }
    )
}

private fun lerpDp(a: Dp, b: Dp, t: Float): Dp = a + (b - a) * t.coerceIn(0f, 1f)