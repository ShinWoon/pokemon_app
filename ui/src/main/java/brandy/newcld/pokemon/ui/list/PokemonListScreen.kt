package brandy.newcld.pokemon.ui.list

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import brandy.newcld.pokemon.presentation.model.PokemonItemModel
import brandy.newcld.pokemon.presentation.viewmodel.PokemonListViewModel
import brandy.newcld.pokemon.ui.theme.Background
import brandy.newcld.pokemon.ui.theme.DarkModeBackground
import brandy.newcld.pokemon.ui.theme.Secondary
import brandy.newcld.pokemon.ui.theme.Typography

private const val TAG = "PokemonListScreen"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonListScreen(
    modifier: Modifier = Modifier,
    pokemonListViewModel: PokemonListViewModel,
    onItemClick : (pid: Int) -> Unit,
    pokemonItemModel: PokemonItemModel
) {
    val items = pokemonListViewModel.pokemonList.collectAsLazyPagingItems()
    val tmpMap by pokemonListViewModel.bgColors.collectAsState()

    val isDarkMode = isSystemInDarkTheme()

    LaunchedEffect(Unit) {
        pokemonListViewModel.getPokemonList()
    }
    Column {
        TopAppBar(
            title = {
                Text(
                    text = "포켓몬 도감",
                    style = Typography.titleLarge,
                    color = if(isDarkMode) Background else Secondary,
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = if(isDarkMode) DarkModeBackground else Background
            ),
        )
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 160.dp),
            modifier = modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(12.dp)
        ) {
            items(
                count = items.itemCount,
                key = { index -> items[index]?.id ?: index },
            ) { index ->
                val pokemon = items[index]
                val tmp = tmpMap[index]
                if(pokemon != null) {
                    PokemonListItem(
                        modifier = modifier,
                        onClick = { onItemClick(pokemon.id) },
                        pokemonItem = pokemon,
                        isDarkMode = isDarkMode,
                        tmpBgColors = tmp,
                        pokemonItemModel = pokemonItemModel,
                        onColorExtracted = { color -> pokemonListViewModel.onColorExtracted(pokemon.id, color) }
                    )
                }
            }
        }
    }
}