package brandy.newcld.pokemon.presentation.viewmodel

import brandy.newcld.pokemon.domain.usecase.GetPokemonDetailLocalInfoUseCase
import brandy.newcld.pokemon.domain.usecase.GetPokemonInfoUseCase
import brandy.newcld.pokemon.domain.usecase.GetPokemonSpeciesUseCase
import brandy.newcld.pokemon.presentation.model.PokemonDetailLocalInfoModel
import brandy.newcld.pokemon.presentation.model.PokemonInfoModel
import brandy.newcld.pokemon.presentation.model.UiState
import brandy.newcld.pokemon.presentation.model.toPokemonDetailLocalInfoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getPokemonDetailUseCase: GetPokemonInfoUseCase,
    private val getPokemonDetailLocalInfoUseCase: GetPokemonDetailLocalInfoUseCase,
    private val getPokemonSpeciesUseCase: GetPokemonSpeciesUseCase
): BaseViewModel() {
    private val _localAppBarInfoUiState = MutableStateFlow<UiState<PokemonDetailLocalInfoModel>>(UiState())
    val localAppBarInfoUiState = _localAppBarInfoUiState

    private val _remotePokemonInfoUiState = MutableStateFlow<UiState<PokemonInfoModel>>(UiState())
    val remotePokemonInfoUiState = _remotePokemonInfoUiState

    fun getPokemonInfo(pid: Int?) {
        val id = pid ?: 1
        combineDataResource(
            fa = getPokemonDetailUseCase(id = id),
            fb = getPokemonSpeciesUseCase(id = id)
        ) { info, description ->
            PokemonInfoModel(
                height = info.height,
                weight = info.weight,
                types = info.types.joinToString(", ") { it.type.name },
                description = description
            )
        }.bind(
            state = _remotePokemonInfoUiState,
            mapper = { it }
        )
    }

    fun loadLocalInfo(pid: Int?) {
        getPokemonDetailLocalInfoUseCase(pid = pid ?: 1).bind(
            state = _localAppBarInfoUiState,
            mapper = { it.toPokemonDetailLocalInfoModel() }
        )
    }
}