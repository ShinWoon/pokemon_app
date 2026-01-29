package brandy.newcld.pokemon.ui.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import brandy.newcld.pokemon.ui.util.ImageUtil.PaletteBackgroundWithImage

@Composable
fun CollapsingAppBar (
    modifier: Modifier = Modifier,
    collapse: Float = 0f,
    boxModifier: Modifier,
    pid: Int? = 1,
) {
    Box(
        modifier = boxModifier
    ) {
        // 펼쳐져있을 때 보임
//        AsyncImage(
//            model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/132.png",
//            contentDescription = "DetailImage",
//            modifier = modifier.size(64.dp)
//        )
        PaletteBackgroundWithImage(
            modifier = modifier,
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${pid}.png",
            boxModifier = modifier.fillMaxWidth(),
            boxShape = RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp),
            imageSize = modifier.size(198.dp).padding(16.dp),
            order = "row",
            content = {
                Column(
                    modifier = modifier.padding(16.dp).graphicsLayer(alpha = 1f - (collapse*collapse)),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text("한글 이름")
                    Text("영어 이름")
                }
            }
        )
    }
}