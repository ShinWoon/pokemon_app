package brandy.newcld.pokemon.ui.list

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import brandy.newcld.pokemon.presentation.viewmodel.PokemonListViewModel
import brandy.newcld.pokemon.ui.util.ImageUtil.PaletteBackgroundWithImage

@Composable
fun PokemonListScreen(
    modifier: Modifier = Modifier,
    pokemonListViewModel: PokemonListViewModel,
) {
    LaunchedEffect(false) {
        pokemonListViewModel.getPokemonList()
    }
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 160.dp),
        modifier = modifier.fillMaxSize()
    ) {
        items(10) {
            PokemonListItem(
                modifier = modifier,
                item = GridItemState(it),
                onLongPress = {},
                onClick = {}
            )
        }
    }
}

@Composable
fun PokemonListItem(
    modifier: Modifier = Modifier,
    item: GridItemState,
    onLongPress: () -> Unit,
    onClick: () -> Unit,
    imageUrl: String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/132.png"
) {
    Box(
        modifier = modifier.combinedClickable(
            onLongClick = { onLongPress() },
            onClick = { onClick() }
        )
    ) {
        if(item.isFlipped) {
            PaletteBackgroundWithImage(
                imageUrl = imageUrl,
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp),
                showText = true
            )
        } else {
            PaletteBackgroundWithImage(
                imageUrl = imageUrl,
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )
        }
    }
}

data class GridItemState (
    val id: Int,
    val isFlipped: Boolean = false
)

@Preview
@Composable
fun PokemonListScreenPreview() {
//    PokemonListScreen()
}
