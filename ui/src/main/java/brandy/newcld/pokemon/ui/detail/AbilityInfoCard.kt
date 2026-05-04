package brandy.newcld.pokemon.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import brandy.newcld.pokemon.presentation.model.PokemonInfoModel
import brandy.newcld.pokemon.presentation.model.UiState
import brandy.newcld.pokemon.ui.theme.Hint
import brandy.newcld.pokemon.ui.theme.Typography

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AbilityInfoCard(
    modifier: Modifier = Modifier,
    isDarkMode: Boolean = false,
    remoteInfo: UiState<PokemonInfoModel>,
    typeColors: PokemonTypeColor,
) {
    val abilities = remoteInfo.data?.abilities

    DetailCommonCard (
        modifier = modifier,
        isDarkMode = isDarkMode,
        title = "특성",
        titleColor = typeColors.textColor,
    ) {
        FlowRow(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            abilities?.forEach { (name, isHidden) ->
                AbilityChip(name = name, isHidden = isHidden, typeColors = typeColors)
            }
        }
    }
}

@Composable
private fun AbilityChip(
    name: String,
    isHidden: Boolean,
    typeColors: PokemonTypeColor,
) {
    val bgColor = if (isHidden) typeColors.background.copy(alpha = 0.3f) else typeColors.background
    val borderColor = if (isHidden) typeColors.border.copy(alpha = 0.4f) else typeColors.border
    val textColor = if (isHidden) typeColors.textColor.copy(alpha = 0.5f) else typeColors.textColor

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(bgColor)
                .border(1.dp, borderColor, RoundedCornerShape(20.dp))
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = name,
                fontSize = 14.sp,
                color = textColor,
                style = Typography.titleSmall,
            )
        }
        if (isHidden) {
            Spacer(Modifier.height(4.dp))
            Text(text = "숨겨진 특성", fontSize = 10.sp, color = Hint, style = Typography.bodyMedium)
        }
    }
}
