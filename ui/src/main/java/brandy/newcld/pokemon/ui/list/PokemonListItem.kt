package brandy.newcld.pokemon.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import brandy.newcld.pokemon.presentation.model.DayNight
import brandy.newcld.pokemon.presentation.model.PokemonItemModel
import brandy.newcld.pokemon.presentation.model.PokemonListItemModel
import brandy.newcld.pokemon.ui.theme.DefaultLightGray
import brandy.newcld.pokemon.ui.theme.Typography
import brandy.newcld.pokemon.ui.util.PaletteUtil.paletteBackgroundColor
import coil3.compose.AsyncImage
import coil3.request.ImageRequest

@Composable
fun PokemonListItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    pokemonItem: PokemonListItemModel,
    pokemonItemModel: PokemonItemModel = PokemonItemModel(0,"","",":"),
    tmpBgColors: DayNight?,
    isDarkMode: Boolean = false,
    onColorExtracted: (Int) -> Unit
) {
    val context = LocalContext.current
    if (pokemonItemModel.dayTimeColor == null || pokemonItemModel.nightTimeColor == null) {
        LaunchedEffect(pokemonItem.id) {
            val color = paletteBackgroundColor(pokemonItem.imageUrl, context)
            onColorExtracted(color)
        }
    }

    val backgroundColor = when {
        isDarkMode && pokemonItemModel.nightTimeColor != null -> Color(pokemonItemModel.nightTimeColor!!.toInt())
        !isDarkMode && pokemonItemModel.dayTimeColor != null -> Color(pokemonItemModel.dayTimeColor!!.toInt())
        isDarkMode -> Color(tmpBgColors!!.night)
        !isDarkMode -> Color(tmpBgColors!!.day)
        else -> DefaultLightGray
    }

    Box(
        modifier = modifier
            .size(176.dp)
            .background(color = backgroundColor, shape = RoundedCornerShape(16.dp))
            .clickable(
                onClick = { onClick() },
            ),
        contentAlignment = Alignment.Center
    ) {
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
                text = pokemonItem.name,
                style = Typography.titleMedium,
                color = Color.White
            )
        }
    }
}
