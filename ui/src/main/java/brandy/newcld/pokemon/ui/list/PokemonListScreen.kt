package brandy.newcld.pokemon.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import brandy.newcld.pokemon.presentation.model.PokemonListItemModel
import brandy.newcld.pokemon.presentation.viewmodel.PokemonListViewModel
import brandy.newcld.pokemon.ui.theme.Typography
import brandy.newcld.pokemon.ui.util.ImageUtil.PaletteBackgroundWithImage

private const val TAG = "PokemonListScreen"

@Composable
fun PokemonListScreen(
    modifier: Modifier = Modifier,
    pokemonListViewModel: PokemonListViewModel,
) {
    val pokemonListState by pokemonListViewModel.pokemonList.collectAsState()

    LaunchedEffect(Unit) {
        pokemonListViewModel.getPokemonList()
    }
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 160.dp),
        modifier = modifier
            .fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(12.dp)
    ) {
        items(
            items = pokemonListState.items,
            key = { it.id },
        ) { pokemonItem ->
            PokemonListItem(
                modifier = modifier,
                onClick = { pokemonListViewModel.onItemClick("") },
                pokemonItem = pokemonItem
            )
        }
    }
}

@Composable
fun PokemonListItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    pokemonItem: PokemonListItemModel,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                onClick = { onClick() },
            ),
        contentAlignment = Alignment.Center
    ) {
        PaletteBackgroundWithImage(
            imageUrl = pokemonItem.imageUrl,
            modifier = modifier
        ) {
            Spacer(modifier = modifier.size(4.dp))
            Text(
                text = pokemonItem.name,
                style = Typography.titleMedium,
                color = Color.White
                )
        }
    }
}
