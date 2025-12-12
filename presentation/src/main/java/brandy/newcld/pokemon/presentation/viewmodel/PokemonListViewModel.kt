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
    private val soundPlayer: SoundPlayer
): BaseViewModel() {
    private val _pokemonList = MutableStateFlow(ListUiState<PokemonListItemModel>())
    val pokemonList = _pokemonList.asStateFlow()

    fun getPokemonList() {
        getPokemonListUseCase().bindList(state = _pokemonList, mapper = NameUrl::toPokemonListItemModel)
    }

    fun onItemClick(oggUrl: String) {
        soundPlayer.play("https://raw.githubusercontent.com/PokeAPI/cries/main/cries/pokemon/latest/35.ogg")
    }
}