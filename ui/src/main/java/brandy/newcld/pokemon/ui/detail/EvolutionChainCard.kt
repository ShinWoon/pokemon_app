package brandy.newcld.pokemon.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import brandy.newcld.pokemon.ui.theme.Typography
import brandy.newcld.pokemon.ui.theme.primaryTextOf
import brandy.newcld.pokemon.ui.theme.secondaryTextOf
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

    val cardBg = if (isDarkMode) DarkModeCardBackground else LightModeCardBackground
    val levels = stages.groupBy { it.depth }.toSortedMap().values.toList()
    val isBranching = levels.any { it.size > 1 }

    DetailCommonCard(
        modifier = modifier,
        isDarkMode = isDarkMode,
        title = "진화",
        titleColor = typeColors.textColor,
    ) {
        if (isBranching) {
            BranchingEvolutionView(
                levels = levels,
                cardBg = cardBg,
                isDarkMode = isDarkMode,
            )
        } else {
            LinearEvolutionRow(
                stages = stages.sortedBy { it.depth },
                cardBg = cardBg,
                isDarkMode = isDarkMode,
            )
        }
    }
}

@Composable
private fun LinearEvolutionRow(
    stages: List<EvolutionStageModel>,
    cardBg: Color,
    isDarkMode: Boolean,
) {
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
    ) {
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
                        color = secondaryTextOf(isDarkMode = isDarkMode),
                        modifier = Modifier.padding(horizontal = 4.dp),
                    )
                }
                EvolutionStageView(stage = stage, isDarkMode = isDarkMode)
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
}

@Composable
private fun BranchingEvolutionView(
    levels: List<List<EvolutionStageModel>>,
    cardBg: Color,
    isDarkMode: Boolean,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, bottom = 12.dp),
    ) {
        levels.forEachIndexed { index, levelStages ->
            if (index > 0) {
                DepthSeparator(isDarkMode = isDarkMode)
            }
            EvolutionLevelRow(
                stages = levelStages,
                cardBg = cardBg,
                isDarkMode = isDarkMode,
            )
        }
    }
}

@Composable
private fun EvolutionLevelRow(
    stages: List<EvolutionStageModel>,
    cardBg: Color,
    isDarkMode: Boolean,
) {
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState)
                .padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            stages.forEach { stage ->
                EvolutionStageView(stage = stage, isDarkMode = isDarkMode)
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
}

@Composable
private fun DepthSeparator(isDarkMode: Boolean) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "▼",
            fontSize = 12.sp,
            color = secondaryTextOf(isDarkMode = isDarkMode),
        )
    }
}

@Composable
private fun EvolutionStageView(stage: EvolutionStageModel, isDarkMode: Boolean) {
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
            color = primaryTextOf(isDarkMode = isDarkMode),
            textAlign = TextAlign.Center,
        )
    }
}
