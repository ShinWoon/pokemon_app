package brandy.newcld.pokemon.ui.detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import brandy.newcld.pokemon.ui.R
import brandy.newcld.pokemon.ui.theme.Secondary
import brandy.newcld.pokemon.ui.theme.Typography
import coil3.compose.AsyncImage

@Composable
fun CollapsedAppBar (
    modifier: Modifier = Modifier,
    boxModifier: Modifier,
    onClickBack: () -> Unit,
    isCollapsed: Boolean,
    imageUrl: String,
    typeImageUrl: String,
    name: String,
) {
    Box(
        modifier = boxModifier,
    ) {
        Row(
            modifier = modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
                ) {
                // 뒤로가기 버튼
                IconButton(
                    onClick = { onClickBack() }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_back_round),
                        contentDescription = "back button icon",
                        modifier = Modifier.size(24.dp),
                        tint = Secondary
                    )
                }
                // 아이콘 & 텍스트
                AnimatedVisibility(
                    visible = isCollapsed,
                    enter = fadeIn(animationSpec = tween(delayMillis = 200, durationMillis = 300)) + expandHorizontally(),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // 앱바 아이콘
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = null,
                            modifier = modifier.size(56.dp),
                            contentScale = ContentScale.Crop
                        )

                        Text(name,
                            style = Typography.titleMedium,
                            color = Secondary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
            // 속성 아이콘
            AsyncImage(
                model = typeImageUrl,
                contentDescription = null,
                modifier = modifier.size(64.dp).padding(end = 16.dp),
                contentScale = ContentScale.Fit,
            )
        }
    }
}