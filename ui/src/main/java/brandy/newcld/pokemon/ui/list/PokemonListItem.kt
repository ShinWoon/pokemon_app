package brandy.newcld.pokemon.ui.list

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import brandy.newcld.pokemon.presentation.model.DayNight
import brandy.newcld.pokemon.presentation.model.PokemonItemLocalModel
import brandy.newcld.pokemon.presentation.model.PokemonListItemModel
import brandy.newcld.pokemon.ui.theme.DarkGray
import brandy.newcld.pokemon.ui.theme.DarkModeCardBackground
import brandy.newcld.pokemon.ui.theme.DefaultLightGray
import brandy.newcld.pokemon.ui.theme.LightText
import brandy.newcld.pokemon.ui.theme.Typography
import brandy.newcld.pokemon.ui.util.PaletteUtil.paletteBackgroundColor
import coil3.compose.AsyncImage
import coil3.request.ImageRequest

@Composable
fun PokemonListItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    pokemonItem: PokemonListItemModel,
    pokemonItemLocalModel: PokemonItemLocalModel,
    tmpBgColors: DayNight?,
    isDarkMode: Boolean = false,
    onColorExtracted: (Int) -> Unit
) {
    val context = LocalContext.current
    if (pokemonItemLocalModel.dayTimeColor == 0 || pokemonItemLocalModel.nightTimeColor == 0) {
        LaunchedEffect(pokemonItem.id) {
            val color = paletteBackgroundColor(pokemonItem.imageUrl, context)
            onColorExtracted(color)
        }
    }

    val isColorReady = if (isDarkMode) {
        pokemonItemLocalModel.nightTimeColor != 0 || tmpBgColors != null
    } else {
        pokemonItemLocalModel.dayTimeColor != 0 || tmpBgColors != null
    }

    val backgroundColor = when {
        isDarkMode && pokemonItemLocalModel.nightTimeColor != 0 -> Color(pokemonItemLocalModel.nightTimeColor)
        !isDarkMode && pokemonItemLocalModel.dayTimeColor != 0 -> Color(pokemonItemLocalModel.dayTimeColor)
        isDarkMode && tmpBgColors != null -> Color(tmpBgColors.night)
        !isDarkMode && tmpBgColors != null -> Color(tmpBgColors.day)
        isDarkMode -> DarkModeCardBackground
        else -> DefaultLightGray
    }
    val nameColor = if (backgroundColor.luminance() > 0.5f) DarkGray else LightText

    Box(
        modifier = modifier
            .size(176.dp)
            .background(color = backgroundColor, shape = RoundedCornerShape(16.dp))
            .clickable(
                onClick = { onClick() },
            ),
        contentAlignment = Alignment.Center
    ) {
        if (!isColorReady) {
            SkeletonContent(isDarkMode = isDarkMode)
        } else {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(pokemonItem.imageUrl)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier.size(100.dp)
                )
                Spacer(modifier = modifier.size(4.dp))
                Text(
                    text = pokemonItemLocalModel.koName,
                    style = Typography.titleMedium,
                    color = nameColor
                )
            }
        }
    }
}

@Composable
private fun SkeletonContent(isDarkMode: Boolean) {
    val brush = rememberShimmerBrush(isDarkMode = isDarkMode)
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(brush = brush, shape = CircleShape)
        )
        Spacer(modifier = Modifier.size(12.dp))
        Box(
            modifier = Modifier
                .width(72.dp)
                .height(14.dp)
                .background(brush = brush, shape = RoundedCornerShape(4.dp))
        )
    }
}

@Composable
private fun rememberShimmerBrush(isDarkMode: Boolean): Brush {
    val transition = rememberInfiniteTransition(label = "shimmer")
    val translate by transition.animateFloat(
        initialValue = -300f,
        targetValue = 600f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "translate",
    )
    val colors = if (isDarkMode) {
        listOf(
            Color(0xFF2E2E2E),
            Color(0xFF3F3F3F),
            Color(0xFF2E2E2E),
        )
    } else {
        listOf(
            Color(0xFFDADADA),
            Color(0xFFEFEFEF),
            Color(0xFFDADADA),
        )
    }
    return Brush.linearGradient(
        colors = colors,
        start = Offset(translate - 200f, 0f),
        end = Offset(translate + 200f, 0f),
    )
}
