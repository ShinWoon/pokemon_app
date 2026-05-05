package brandy.newcld.pokemon.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import brandy.newcld.pokemon.dataresource.DataResource
import brandy.newcld.pokemon.domain.usecase.GetAbilityUseCase
import brandy.newcld.pokemon.domain.usecase.GetEvolutionChainUseCase
import brandy.newcld.pokemon.domain.usecase.GetPokemonDetailLocalInfoUseCase
import brandy.newcld.pokemon.domain.usecase.GetPokemonInfoUseCase
import brandy.newcld.pokemon.domain.usecase.GetPokemonSpeciesUseCase
import brandy.newcld.pokemon.presentation.model.AbilityModel
import brandy.newcld.pokemon.presentation.model.EvolutionChainModel
import brandy.newcld.pokemon.presentation.model.PokemonDetailLocalInfoModel
import brandy.newcld.pokemon.presentation.model.PokemonInfoModel
import brandy.newcld.pokemon.presentation.model.UiState
import brandy.newcld.pokemon.presentation.model.toPokemonDetailLocalInfoModel
import brandy.newcld.pokemon.presentation.model.toPresentationModel
import brandy.newcld.pokemon.presentation.util.SoundPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getPokemonDetailUseCase: GetPokemonInfoUseCase,
    private val getPokemonDetailLocalInfoUseCase: GetPokemonDetailLocalInfoUseCase,
    private val getPokemonSpeciesUseCase: GetPokemonSpeciesUseCase,
    private val getAbilityUseCase: GetAbilityUseCase,
    private val getEvolutionChainUseCase: GetEvolutionChainUseCase,
    private val soundPlayer: SoundPlayer,
): BaseViewModel() {
    private val _localAppBarInfoUiState = MutableStateFlow(UiState<PokemonDetailLocalInfoModel>(isLoading = true))
    val localAppBarInfoUiState = _localAppBarInfoUiState

    private val _remotePokemonInfoUiState = MutableStateFlow(UiState<PokemonInfoModel>(isLoading = true))
    val remotePokemonInfoUiState = _remotePokemonInfoUiState

    private val _descriptionUiState = MutableStateFlow(UiState<String>(isLoading = true))
    val descriptionUiState = _descriptionUiState

    private val _evolutionChainUiState = MutableStateFlow(UiState<EvolutionChainModel>(isLoading = true))
    val evolutionChainUiState = _evolutionChainUiState

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
        getEvolutionChainUseCase(pokemonId = id).bind(
            state = _evolutionChainUiState,
            mapper = { it.toPresentationModel() }
        )
        observeAbilityNames()
    }

    private fun observeAbilityNames() {
        viewModelScope.launch {
            val names = _remotePokemonInfoUiState
                .filter { it.data != null }
                .map { it.data!!.abilities.map(AbilityModel::name) }
                .filter { it.isNotEmpty() }
                .distinctUntilChanged()
                .first()
            names.forEach { name -> fetchAbilityTranslation(name) }
        }
    }

    private fun fetchAbilityTranslation(name: String) {
        getAbilityUseCase(name)
            .onEach { res ->
                if (res is DataResource.Success) {
                    val koName = res.data.koName
                    _remotePokemonInfoUiState.update { current ->
                        val data = current.data ?: return@update current
                        current.copy(
                            data = data.copy(
                                abilities = data.abilities.map { ability ->
                                    if (ability.name == name) ability.copy(koName = koName) else ability
                                }
                            )
                        )
                    }
                }
            }
            .launchIn(viewModelScope)
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
