package brandy.newcld.pokemon.ui.detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import brandy.newcld.pokemon.ui.theme.PrimaryText
import brandy.newcld.pokemon.ui.theme.Typography

@Composable
fun DescriptionCard(
    modifier: Modifier = Modifier,
    isDarkMode: Boolean = false,
    descriptionText: String,
    typeColors: PokemonTypeColor
) {
    if(descriptionText != "") {
        DetailCommonCard (
            modifier = modifier,
            isDarkMode = isDarkMode
        ) {
            Text(text = descriptionText, fontSize = 14.sp, style = Typography.titleMedium, color = lerp(typeColors.textColor, Color.Black, 0.25f), lineHeight = 22.sp, modifier = modifier.padding(horizontal = 16.dp, vertical = 12.dp))
        }
    }
}