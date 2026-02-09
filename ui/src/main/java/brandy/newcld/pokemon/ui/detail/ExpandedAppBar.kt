package brandy.newcld.pokemon.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import brandy.newcld.pokemon.ui.theme.DarkGray
import brandy.newcld.pokemon.ui.theme.Hint
import brandy.newcld.pokemon.ui.theme.Typography
import coil3.compose.AsyncImage
import coil3.request.ImageRequest

@Composable
fun ExpandedAppBar(
    modifier: Modifier = Modifier,
    boxModifier: Modifier,
    imageUrl: String,
    koName: String,
    engName: String,
) {
    Box (
        modifier = boxModifier
    ) {
        Row(
            modifier = modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(200.dp),
                alignment = Alignment.BottomCenter
            )
            Column(
                modifier = modifier.padding(end = 16.dp, bottom = 16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    koName,
                    style = Typography.titleLarge,
                    color = DarkGray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    engName,
                    style = Typography.titleSmall,
                    color = Hint,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}