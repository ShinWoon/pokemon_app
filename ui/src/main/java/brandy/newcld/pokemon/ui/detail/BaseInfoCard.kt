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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import brandy.newcld.pokemon.presentation.model.PokemonInfoModel
import brandy.newcld.pokemon.presentation.model.UiState
import brandy.newcld.pokemon.ui.R
import brandy.newcld.pokemon.ui.theme.PrimaryText
import brandy.newcld.pokemon.ui.theme.SecondaryText
import brandy.newcld.pokemon.ui.theme.Typography

@Composable
fun BaseInfoCard(
    modifier: Modifier = Modifier,
    isDarkMode: Boolean = false,
    remoteInfo: UiState<PokemonInfoModel>,
    typeColors: PokemonTypeColor,
) {
    DetailCommonCard (
        modifier = modifier,
        isDarkMode = isDarkMode,
        title = "기본 정보",
        titleColor = typeColors.textColor,
    ) {
        Row(
            modifier = modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            BasicInfoItem(label = "키", num = (remoteInfo.data?.height?.toDouble()?.div(10)).toString(), unit = "m" ,icon = R.drawable.height_round, contentDesc = "키 아이콘")
            VerticalDivider()
            BasicInfoItem(label = "몸무게", num = (remoteInfo.data?.weight?.toDouble()?.div(10)).toString(), unit = "kg", icon = R.drawable.scale_round, contentDesc = "몸무게 아이콘")
            VerticalDivider()
            BasicInfoItem(label = "기초 경험치", num = remoteInfo.data?.baseExperience.toString(), unit = "XP", icon = R.drawable.vital_signs_round, contentDesc = "기초 경험 아이콘")
        }
    }
}

@Composable
fun BasicInfoItem(modifier: Modifier = Modifier, label: String, num: String, unit: String, icon: Int, contentDesc: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "$num $unit", fontSize = 16.sp, style = Typography.titleMedium, color = PrimaryText)
        Spacer(modifier = modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(icon), contentDescription = contentDesc, tint = SecondaryText, modifier = modifier.size(16.dp).padding(end = 4.dp))
            Text(text = label, fontSize = 12.sp, style = Typography.titleSmall, color = SecondaryText)
        }
    }
}