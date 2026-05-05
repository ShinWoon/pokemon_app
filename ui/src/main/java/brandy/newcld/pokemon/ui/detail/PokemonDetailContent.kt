package brandy.newcld.pokemon.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import brandy.newcld.pokemon.presentation.model.EvolutionChainModel
import brandy.newcld.pokemon.presentation.model.PokemonInfoModel
import brandy.newcld.pokemon.presentation.model.UiState

@Composable
fun PokemonDetailContent(
    modifier: Modifier = Modifier,
    isDarkMode: Boolean = false,
    remoteInfo: UiState<PokemonInfoModel>,
    descriptionInfo: UiState<String>,
    evolutionChainInfo: UiState<EvolutionChainModel>,
    onPlayCry: (String) -> Unit = {},
) {
    val typeName = remoteInfo.data?.type.orEmpty()
    val typeColors = remember(typeName, isDarkMode) {
        PokemonTypeColors.getColor(pokemonTypeOf(typeName), isDarkMode)
    }

    Column(
        modifier = modifier.padding(bottom = 8.dp)
    ) {
        // 설명 카드
        DescriptionCard(modifier = modifier, isDarkMode = isDarkMode, descriptionText = descriptionInfo.data ?: "설명 없음", typeColors = typeColors)

        // 키 몸무게 경험치
        BaseInfoCard(modifier = modifier, isDarkMode = isDarkMode, remoteInfo = remoteInfo, typeColors = typeColors)

        // 진화 카드
        EvolutionChainCard(modifier = modifier, isDarkMode = isDarkMode, evolutionChainInfo = evolutionChainInfo, typeColors = typeColors)

        // 스탯 정보 카드
        StatInfoCard(modifier = modifier, isDarkMode = isDarkMode, remoteInfo = remoteInfo, typeColors = typeColors)

        // 특성 카드
        AbilityInfoCard(modifier = modifier, isDarkMode = isDarkMode, remoteInfo = remoteInfo, typeColors = typeColors)

        // 소리 카드
        CryCard(modifier = modifier, isDarkMode = isDarkMode, remoteInfo = remoteInfo, onPlay = onPlayCry, typeColors = typeColors)
    }
}
