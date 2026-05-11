package brandy.newcld.pokemon.ui.list

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import brandy.newcld.pokemon.dataresource.toAppError
import brandy.newcld.pokemon.presentation.model.SearchResultItem
import brandy.newcld.pokemon.presentation.viewmodel.PokemonListViewModel
import brandy.newcld.pokemon.ui.state.ErrorScreen
import brandy.newcld.pokemon.ui.state.LoadingScreen

private const val TAG = "PokemonListScreen"

@Composable
fun PokemonListScreen(
    modifier: Modifier = Modifier,
    pokemonListViewModel: PokemonListViewModel,
    onItemClick: (pid: Int) -> Unit,
) {
    val items = pokemonListViewModel.pokemonList.collectAsLazyPagingItems()
    val localById by pokemonListViewModel.localById.collectAsState()
    val tmpMap by pokemonListViewModel.bgColors.collectAsState()
    val isSearching by pokemonListViewModel.isSearching.collectAsState()
    val searchQuery by pokemonListViewModel.searchQuery.collectAsState()
    val searchResults by pokemonListViewModel.searchResults.collectAsState()

    val isDarkMode = isSystemInDarkTheme()
    val refresh = items.loadState.refresh
    val isReady = refresh is LoadState.NotLoading && items.itemCount > 0 && localById.isNotEmpty()

    BackHandler(enabled = isSearching) {
        pokemonListViewModel.setSearchActive(false)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Crossfade(
            targetState = isSearching,
            animationSpec = tween(durationMillis = 220),
            label = "topbar-crossfade",
        ) { searching ->
            if (searching) {
                SearchTopBar(
                    query = searchQuery,
                    onQueryChange = pokemonListViewModel::updateSearchQuery,
                    onClose = { pokemonListViewModel.setSearchActive(false) },
                    isDarkMode = isDarkMode,
                )
            } else {
                PokemonListTopBar(
                    onSearchClick = { pokemonListViewModel.setSearchActive(true) },
                    isDarkMode = isDarkMode,
                )
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            when {
                refresh is LoadState.Error -> ErrorScreen(
                    error = refresh.error.toAppError(),
                    isDarkMode = isDarkMode,
                    onRetry = { items.retry() },
                )
                !isReady -> LoadingScreen(isDarkMode = isDarkMode)
                else -> LazyVerticalGrid(
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
                        val pokemonLocal = pokemon?.let { localById[it.id] }

                        if (pokemon == null || pokemonLocal == null) {
                            PokemonListItemSkeleton(isDarkMode = isDarkMode)
                            return@items
                        }

                        val tmp = tmpMap[pokemon.id]
                        PokemonListItem(
                            modifier = modifier,
                            onClick = { onItemClick(pokemon.id) },
                            pokemonItem = pokemon,
                            isDarkMode = isDarkMode,
                            tmpBgColors = tmp,
                            pokemonItemLocalModel = pokemonLocal,
                            onColorExtracted = { color -> pokemonListViewModel.onColorExtracted(pokemon.id, color) }
                        )
                    }
                }
            }


            AnimatedSearchOverlay(
                visible = isSearching,
                query = searchQuery,
                results = searchResults,
                isDarkMode = isDarkMode,
                onItemClick = { pid ->
                    pokemonListViewModel.setSearchActive(false)
                    onItemClick(pid)
                },
            )
        }
    }
}

@Composable
private fun AnimatedSearchOverlay(
    visible: Boolean,
    query: String,
    results: List<SearchResultItem>,
    isDarkMode: Boolean,
    onItemClick: (Int) -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(
            animationSpec = tween(durationMillis = 240),
            initialOffsetY = { -it / 4 },
        ) + fadeIn(animationSpec = tween(durationMillis = 240)),
        exit = slideOutVertically(
            animationSpec = tween(durationMillis = 220),
            targetOffsetY = { -it / 4 },
        ) + fadeOut(animationSpec = tween(durationMillis = 220)),
    ) {
        SearchResultDropdown(
            query = query,
            results = results,
            onItemClick = onItemClick,
            isDarkMode = isDarkMode,
        )
    }
}
