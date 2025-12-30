package brandy.newcld.pokemon.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import brandy.newcld.pokemon.domain.usecase.GetPokemonListUseCase
import brandy.newcld.pokemon.presentation.model.PokemonListItemModel
import brandy.newcld.pokemon.presentation.model.toPokemonListItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase,
): BaseViewModel() {
    private val _pokemonList = MutableStateFlow<PagingData<PokemonListItemModel>>(PagingData.empty())
    val pokemonList = _pokemonList.asStateFlow()

    fun getPokemonList() {
        viewModelScope.launch {
            getPokemonListUseCase()
                .map { pagingData ->
                    pagingData.map { pokemon ->
                        pokemon.toPokemonListItemModel()
                    }
                }
                .cachedIn(viewModelScope)
                .collectLatest { pagingData ->
                    _pokemonList.value = pagingData
                }
        }
    }
}