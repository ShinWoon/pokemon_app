package brandy.newcld.pokemon.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import brandy.newcld.pokemon.presentation.model.PokemonInfoModel
import brandy.newcld.pokemon.presentation.model.UiState

@Composable
fun PokemonDetailContent(
    modifier: Modifier = Modifier,
    isDarkMode: Boolean = false,
    remoteInfo: UiState<PokemonInfoModel>,
    descriptionInfo: UiState<String>,
    onPlayCry: (String) -> Unit = {},
) {
    Column {
        // 설명 카드
        DescriptionCard(modifier = modifier, isDarkMode = isDarkMode, descriptionText = descriptionInfo.data ?: "설명 없음")

        // 키 몸무게 경험치
        BaseInfoCard(modifier = modifier, isDarkMode = isDarkMode, remoteInfo = remoteInfo)

        // 스탯 정보 카드
        StatInfoCard(modifier = modifier, isDarkMode = isDarkMode, remoteInfo = remoteInfo)

        // 특성 카드
//        AbilityInfoCard(modifier = modifier, isDarkMode = isDarkMode, remoteInfo = remoteInfo)

        // 기술 카드


        // 소리 카드
        CryCard(modifier = modifier, isDarkMode = isDarkMode, remoteInfo = remoteInfo, onPlay = onPlayCry)
    }
}