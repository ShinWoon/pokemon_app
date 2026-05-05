package brandy.newcld.pokemon.ui.detail

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import brandy.newcld.pokemon.presentation.model.PokemonInfoModel
import brandy.newcld.pokemon.presentation.model.UiState
import brandy.newcld.pokemon.ui.R
import brandy.newcld.pokemon.ui.theme.SecondaryText
import brandy.newcld.pokemon.ui.theme.Typography

@Composable
fun CryCard(
    modifier: Modifier = Modifier,
    isDarkMode: Boolean = false,
    remoteInfo: UiState<PokemonInfoModel>,
    onPlay: (String) -> Unit,
    typeColors: PokemonTypeColor,
) {
    val latest = remoteInfo.data?.cryLatestUrl.orEmpty()
    val legacy = remoteInfo.data?.cryLegacyUrl.orEmpty()

    DetailCommonCard(
        modifier = modifier,
        isDarkMode = isDarkMode,
        title = "울음소리",
        titleColor = typeColors.textColor,
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            SoundButtonView(txt = "최신 소리", enabled = latest.isNotBlank(), onClick = {onPlay(latest)})
            VerticalDivider()
            SoundButtonView(txt = "예전 소리", enabled = legacy.isNotBlank(), onClick = {onPlay(legacy)})
        }
    }
}

@Composable
private fun SoundButtonView(
    modifier: Modifier = Modifier,
    txt: String = "",
    enabled: Boolean,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val iconScale by animateFloatAsState(
        targetValue = if (isPressed) 1.2f else 1f,
        label = "soundButtonIconScale"
    )

    Column(
        modifier = modifier.clickable(
            interactionSource = interactionSource,
            indication = null,
            enabled = enabled,
            onClick = onClick
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(R.drawable.volume_down_round),
            tint = SecondaryText,
            contentDescription = "$txt 아이콘",
            modifier = modifier
                .size(24.dp)
                .scale(iconScale)
        )
        Spacer(modifier = modifier.size(8.dp))
        Text(
            text = txt,
            style = Typography.titleSmall,
            fontSize = 12.sp,
            color = SecondaryText
        )
    }
}