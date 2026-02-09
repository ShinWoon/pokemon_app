package brandy.newcld.pokemon.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import brandy.newcld.pokemon.presentation.model.PokemonListItemModel
import brandy.newcld.pokemon.presentation.viewmodel.PokemonListViewModel
import brandy.newcld.pokemon.ui.theme.Background
import brandy.newcld.pokemon.ui.theme.Secondary
import brandy.newcld.pokemon.ui.theme.Typography
import brandy.newcld.pokemon.ui.util.ImageUtil.PaletteBackground
import coil3.compose.AsyncImage
import coil3.request.ImageRequest

private const val TAG = "PokemonListScreen"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonListScreen(
    modifier: Modifier = Modifier,
    pokemonListViewModel: PokemonListViewModel,
    onItemClick : (pid: Int) -> Unit
) {
    val items = pokemonListViewModel.pokemonList.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        pokemonListViewModel.getPokemonList()
    }
    Column {
        TopAppBar(
            title = {
                Text(
                    text = "포켓몬 도감",
                    style = Typography.titleLarge,
                    color = Secondary,
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Background
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
                if(pokemon != null) {
                    PokemonListItem(
                        modifier = modifier,
                        onClick = { onItemClick(pokemon.id) },
                        pokemonItem = pokemon
                    )
                }
            }
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
        PaletteBackground(
            imageUrl = pokemonItem.imageUrl,
            modifier = modifier,
            boxModifier = modifier.size(176.dp),
            boxShape = RoundedCornerShape(16.dp),
            pid = pokemonItem.id,
            content = {
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
        )
    }
}
