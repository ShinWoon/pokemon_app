package brandy.newcld.pokemon.ui.state

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import brandy.newcld.pokemon.dataresource.AppError
import brandy.newcld.pokemon.ui.R
import brandy.newcld.pokemon.ui.theme.DarkButton
import brandy.newcld.pokemon.ui.theme.DarkButtonText
import brandy.newcld.pokemon.ui.theme.DarkGray
import brandy.newcld.pokemon.ui.theme.LightButton
import brandy.newcld.pokemon.ui.theme.PrimaryText
import brandy.newcld.pokemon.ui.theme.Typography
import brandy.newcld.pokemon.ui.theme.primaryTextOf

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    error: AppError,
    isDarkMode: Boolean,
    onRetry: () -> Unit,
) {
    val (iconRes, message) = when (error) {
        AppError.Network    -> R.drawable.pikachu_error to "인터넷 연결을 확인해주세요"
        is AppError.Server  -> R.drawable.data_fail to "서버에 문제가 발생했어요 (${error.code})"
        AppError.Parse      -> R.drawable.data_fail to "데이터를 읽어올 수 없어요"
        AppError.Empty      -> R.drawable.data_fail to "표시할 정보가 없어요"
        is AppError.Unknown -> R.drawable.data_fail to "알 수 없는 오류가 발생했어요"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(iconRes),
            contentDescription = "에러 이미지",
            modifier = modifier.size(180.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = modifier.size(8.dp))
        Text(text = message, style = Typography.titleLarge, fontSize = 16.sp, color = primaryTextOf(isDarkMode = isDarkMode))
        Button(
            onClick = onRetry,
            modifier = Modifier.padding(top = 8.dp),
            colors = ButtonColors(
                containerColor = if (isDarkMode) DarkButton else LightButton,
                contentColor = if (isDarkMode) DarkButtonText else Color.White,
                disabledContainerColor = DarkGray,
                disabledContentColor = PrimaryText
            )
        ) {
            Text(text = "다시 시도", style = Typography.titleSmall, fontSize = 14.sp)
        }
    }
}
