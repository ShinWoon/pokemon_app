package brandy.newcld.pokemon.presentation.viewmodel

import android.util.Log
import brandy.newcld.pokemon.domain.model.NameUrl
import brandy.newcld.pokemon.domain.usecase.GetPokemonListUseCase
import brandy.newcld.pokemon.presentation.model.ListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

private const val TAG = "PokemonListViewModel"

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase,
): BaseViewModel() {
    private val _pokemonList = MutableStateFlow(ListUiState<NameUrl>())
    val pokemonList = _pokemonList.asStateFlow()

    fun getPokemonList() {
        getPokemonListUseCase().bindList(_pokemonList)
        Log.d(TAG, "getPokemonList: ${pokemonList.value}")
    }
}