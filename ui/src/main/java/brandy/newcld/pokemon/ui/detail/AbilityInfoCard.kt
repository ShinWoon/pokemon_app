package brandy.newcld.pokemon.ui.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import brandy.newcld.pokemon.presentation.model.PokemonInfoModel
import brandy.newcld.pokemon.presentation.model.UiState

@Composable
fun AbilityInfoCard(
    modifier: Modifier = Modifier,
    isDarkMode: Boolean = false,
    remoteInfo: UiState<PokemonInfoModel>,
) {
    val abilities = remoteInfo.data?.abilities

    DetailCommonCard (
        modifier = modifier,
        isDarkMode = isDarkMode,
        title = "특성"
    ) {

    }
}