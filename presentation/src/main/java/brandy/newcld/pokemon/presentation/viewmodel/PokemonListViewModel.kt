package brandy.newcld.pokemon.presentation.viewmodel

import brandy.newcld.pokemon.domain.model.NameUrl
import brandy.newcld.pokemon.domain.usecase.GetPokemonListUseCase
import brandy.newcld.pokemon.presentation.model.ListUiState
import brandy.newcld.pokemon.presentation.model.PokemonListItemModel
import brandy.newcld.pokemon.presentation.model.toPokemonListItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase,
): BaseViewModel() {
    private val _pokemonList = MutableStateFlow(ListUiState<PokemonListItemModel>())
    val pokemonList = _pokemonList.asStateFlow()

    private val _offset = MutableStateFlow(0)
    val offset = _offset.asStateFlow()

    fun getPokemonList() {
        getPokemonListUseCase(limit = LIMIT, offset = offset.value).bindList(state = _pokemonList, mapper = NameUrl::toPokemonListItemModel)
    }

    fun setOffset(offset: Int) {
        _offset.value = offset
    }

    companion object {
        private const val LIMIT = 20
    }
}