package brandy.newcld.pokemon.presentation.viewmodel

import androidx.paging.PagingData
import brandy.newcld.pokemon.domain.usecase.GetPokemonListUseCase
import brandy.newcld.pokemon.presentation.model.PokemonListItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase,
): BaseViewModel() {
    private val _pokemonList = MutableStateFlow(flowOf(PagingData<PokemonListItemModel>))
    val pokemonList = _pokemonList.asStateFlow()

    private val _offset = MutableStateFlow(0)
    val offset = _offset.asStateFlow()

    fun getPokemonList() {
        getPokemonListUseCase(limit = LIMIT, offset = offset.value).
    }

    fun setOffset(offset: Int) {
        _offset.value = offset
    }

    companion object {
        private const val LIMIT = 20
    }
}