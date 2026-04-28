package brandy.newcld.pokemon.presentation.viewmodel

import brandy.newcld.pokemon.domain.usecase.GetPokemonDetailLocalInfoUseCase
import brandy.newcld.pokemon.domain.usecase.GetPokemonInfoUseCase
import brandy.newcld.pokemon.domain.usecase.GetPokemonSpeciesUseCase
import brandy.newcld.pokemon.presentation.model.PokemonDetailLocalInfoModel
import brandy.newcld.pokemon.presentation.model.PokemonInfoModel
import brandy.newcld.pokemon.presentation.model.UiState
import brandy.newcld.pokemon.presentation.model.toPokemonDetailLocalInfoModel
import brandy.newcld.pokemon.presentation.model.toPresentationModel
import brandy.newcld.pokemon.presentation.util.SoundPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getPokemonDetailUseCase: GetPokemonInfoUseCase,
    private val getPokemonDetailLocalInfoUseCase: GetPokemonDetailLocalInfoUseCase,
    private val getPokemonSpeciesUseCase: GetPokemonSpeciesUseCase,
    private val soundPlayer: SoundPlayer,
): BaseViewModel() {
    private val _localAppBarInfoUiState = MutableStateFlow(UiState<PokemonDetailLocalInfoModel>(isLoading = true))
    val localAppBarInfoUiState = _localAppBarInfoUiState

    private val _remotePokemonInfoUiState = MutableStateFlow(UiState<PokemonInfoModel>(isLoading = true))
    val remotePokemonInfoUiState = _remotePokemonInfoUiState

    private val _descriptionUiState = MutableStateFlow(UiState<String>(isLoading = true))
    val descriptionUiState = _descriptionUiState

    fun getPokemonInfo(pid: Int?) {
        val id = pid ?: 1
        getPokemonDetailUseCase(id = id).bind(
            state = _remotePokemonInfoUiState,
            mapper = { it.toPresentationModel() }
        )
        getPokemonSpeciesUseCase(id = id).bind(
            state = _descriptionUiState,
            mapper = { it }
        )
    }

    fun loadLocalInfo(pid: Int?) {
        getPokemonDetailLocalInfoUseCase(pid = pid ?: 1).bind(
            state = _localAppBarInfoUiState,
            mapper = { it.toPokemonDetailLocalInfoModel() }
        )
    }

    fun playCry(url: String) {
        if (url.isBlank()) return
        soundPlayer.play(url)
    }

    override fun onCleared() {
        soundPlayer.release()
        super.onCleared()
    }
}
