package brandy.newcld.pokemon.ui.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned


@Composable
fun MainAppBarTitleAnchor(
    modifier: Modifier = Modifier,
    title: String,
    onCoords: (LayoutCoordinates) -> Unit
) {
    Text(
        text = title,
        maxLines = 1,
        modifier = modifier
            .onGloballyPositioned(onCoords)
            .graphicsLayer(alpha = 0f)
    )
}