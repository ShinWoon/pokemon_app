package brandy.newcld.pokemon.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import brandy.newcld.pokemon.presentation.model.EvolutionChainModel
import brandy.newcld.pokemon.presentation.model.EvolutionStageModel
import brandy.newcld.pokemon.presentation.model.UiState
import brandy.newcld.pokemon.ui.theme.DarkModeCardBackground
import brandy.newcld.pokemon.ui.theme.LightModeCardBackground
import brandy.newcld.pokemon.ui.theme.PrimaryText
import brandy.newcld.pokemon.ui.theme.SecondaryText
import brandy.newcld.pokemon.ui.theme.Typography
import coil3.compose.AsyncImage

private val EDGE_FADE_WIDTH = 24.dp

@Composable
fun EvolutionChainCard(
    modifier: Modifier = Modifier,
    isDarkMode: Boolean = false,
    evolutionChainInfo: UiState<EvolutionChainModel>,
    typeColors: PokemonTypeColor,
) {
    val stages = evolutionChainInfo.data?.stages.orEmpty()
    if (stages.isEmpty()) return

    val scrollState = rememberScrollState()
    val cardBg = if (isDarkMode) DarkModeCardBackground else LightModeCardBackground

    DetailCommonCard(
        modifier = modifier,
        isDarkMode = isDarkMode,
        title = "진화",
        titleColor = typeColors.textColor,
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(scrollState)
                        .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    stages.forEachIndexed { index, stage ->
                        if (index > 0) {
                            Text(
                                text = "▶",
                                fontSize = 14.sp,
                                color = SecondaryText,
                                modifier = Modifier.padding(horizontal = 4.dp),
                            )
                        }
                        EvolutionStageView(stage = stage)
                    }
                }

                if (scrollState.canScrollBackward) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .fillMaxHeight()
                            .width(EDGE_FADE_WIDTH)
                            .background(
                                Brush.horizontalGradient(listOf(cardBg, Color.Transparent))
                            ),
                    )
                }
                if (scrollState.canScrollForward) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .fillMaxHeight()
                            .width(EDGE_FADE_WIDTH)
                            .background(
                                Brush.horizontalGradient(listOf(Color.Transparent, cardBg))
                            ),
                    )
                }
            }
            if (scrollState.canScrollForward) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp, bottom = 8.dp),
                    horizontalArrangement = Arrangement.End,
                ) {
                    Text(
                        text = "이동 ▶",
                        fontSize = 12.sp,
                        color = SecondaryText,
                    )
                }
            }
        }
    }
}

@Composable
private fun EvolutionStageView(stage: EvolutionStageModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(80.dp),
    ) {
        Box(
            modifier = Modifier.size(72.dp),
            contentAlignment = Alignment.Center,
        ) {
            AsyncImage(
                model = stage.spriteUrl,
                contentDescription = "${stage.displayName} 이미지",
                modifier = Modifier.size(72.dp),
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stage.displayName,
            fontSize = 12.sp,
            style = Typography.titleSmall,
            color = PrimaryText,
            textAlign = TextAlign.Center,
        )
    }
}
