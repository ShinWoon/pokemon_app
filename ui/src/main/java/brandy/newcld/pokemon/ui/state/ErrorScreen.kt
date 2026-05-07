package brandy.newcld.pokemon.ui.state

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import brandy.newcld.pokemon.ui.R
import coil3.compose.AsyncImage

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    message: String,
    onRetry: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = painterResource(R.drawable.pikachu_error),
            contentDescription = "에러 이미지",
            modifier = modifier.size(160.dp),
            contentScale = ContentScale.Fit
        )
        Text(text = message)
        Button(onClick = onRetry, modifier = Modifier.padding(top = 16.dp)) {
            Text(text = "다시 시도")
        }
    }
}