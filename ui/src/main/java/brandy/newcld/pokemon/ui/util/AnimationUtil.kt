package brandy.newcld.pokemon.ui.util

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.positionInRoot

object AnimationUtil {
    @Composable
    fun MovingTitleOverlay(
        modifier: Modifier = Modifier,
        fraction: Float, // 0 펼칩 ~ 1 접힘
        expanded: LayoutCoordinates?,
        collapsed: LayoutCoordinates?,
        handoffAt: Float = 0.92f,
        content: @Composable () -> Unit
    ) {
        if(expanded == null || collapsed == null) return

        val f = fraction.coerceIn(0f, 1f)

        val start = expanded.positionInRoot()
        val end = collapsed.positionInRoot()

        val x = linearInterpolation(start.x, end.x, f)
        val y = linearInterpolation(start.y, end.y, f)

        val scale = linearInterpolation(1.15f, 1.0f, f)

        val alpha = if(f >= handoffAt) linearInterpolation(1f, 0f, (f-handoffAt)/(1f - handoffAt)) else 1f

        Box(
            modifier = modifier.graphicsLayer {
                translationX = x
                translationY = y
                scaleX = scale
                scaleY = scale
                this.alpha = alpha
            }
        ) {
            content()
        }
    }

    private fun linearInterpolation(a: Float, b: Float, t: Float): Float = a + (b - a) * t.coerceIn(0f, 1f)
}