package brandy.newcld.pokemon.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import brandy.newcld.pokemon.presentation.model.PokemonInfoModel
import brandy.newcld.pokemon.presentation.model.UiState
import brandy.newcld.pokemon.ui.theme.MediumGray
import brandy.newcld.pokemon.ui.theme.PrimaryText
import brandy.newcld.pokemon.ui.theme.SecondaryText
import brandy.newcld.pokemon.ui.theme.Typography

@Composable
fun StatInfoCard(
    modifier: Modifier = Modifier,
    isDarkMode: Boolean = false,
    remoteInfo: UiState<PokemonInfoModel>,
    typeColors: PokemonTypeColor,
) {
    val stats = remoteInfo.data?.stats ?: return
    DetailCommonCard (
        modifier = modifier,
        isDarkMode = isDarkMode,
        title = "스탯 정보",
        titleColor = typeColors.textColor,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 12.dp),) {
            stats.forEach { stat ->
                TextStatRow(
                    txt = StatLabel[stat.name] ?: stat.name,
                    num = stat.value,
                    color = statColor(stat.name, isDarkMode),
                )
            }
        }
    }
}

@Composable
fun TextStatRow(
    modifier: Modifier = Modifier,
    txt: String = "",
    num: Int = 0,
    color: Color = Color.Gray
) {
    val maxStat = 225
    val barWidth = 174.dp

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = txt, fontSize = 14.sp, style = Typography.titleMedium, color = PrimaryText)

        Spacer(modifier = modifier.weight(1f))

        Text(
            text = num.toString().padStart(3),
            fontSize = 13.sp,
            style = Typography.titleMedium,
            color = SecondaryText,
            modifier = Modifier.width(32.dp)
        )
        Spacer(modifier.width(8.dp))
        Box(
            modifier = modifier
                .width(barWidth)
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp)).background(MediumGray)
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth(num / maxStat.toFloat())
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(color)
            )
        }
    }
}

private val StatLabel = mapOf(
    "hp" to "HP",
    "attack" to "공격",
    "defense" to "방어",
    "special-attack" to "특수공격",
    "special-defense" to "특수방어",
    "speed" to "스피드",
)

@Composable
private fun statColor(name: String, isDark: Boolean): Color = when (name) {
    "hp"              -> if (isDark) Color(0xFFEF9A9A) else Color(0xFFE57373)
    "attack"          -> if (isDark) Color(0xFFFFAB91) else Color(0xFFFF8A65)
    "defense"         -> if (isDark) Color(0xFFFFE082) else Color(0xFFFFD54F)
    "special-attack"  -> if (isDark) Color(0xFF90CAF9) else Color(0xFF64B5F6)
    "special-defense" -> if (isDark) Color(0xFFA5D6A7) else Color(0xFF81C784)
    "speed"           -> if (isDark) Color(0xFFF48FB1) else Color(0xFFF06292)
    else              -> Color.Gray
}