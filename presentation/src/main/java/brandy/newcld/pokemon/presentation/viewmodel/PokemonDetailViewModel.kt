package brandy.newcld.pokemon.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import brandy.newcld.pokemon.domain.usecase.GetPokemonDetailLocalInfoUseCase
import brandy.newcld.pokemon.domain.usecase.GetPokemonInfoUseCase
import brandy.newcld.pokemon.presentation.model.PokemonDetailLocalInfoModel
import brandy.newcld.pokemon.presentation.model.PokemonInfoModel
import brandy.newcld.pokemon.presentation.model.UiState
import brandy.newcld.pokemon.presentation.model.toPokemonDetailLocalInfoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getPokemonDetailUseCase: GetPokemonInfoUseCase,
    private val getPokemonDetailLocalInfoUseCase: GetPokemonDetailLocalInfoUseCase
): BaseViewModel() {
    private val _localAppBarInfoUiState = MutableStateFlow<UiState<PokemonDetailLocalInfoModel>>(UiState())
    val localAppBarInfoUiState = _localAppBarInfoUiState

    private val _remotePokemonInfoUiState = MutableStateFlow<UiState<PokemonInfoModel>>(UiState())
    val remotePokemonInfoUiState = _remotePokemonInfoUiState

//    private val _pokemonAppBarInfo = MutableStateFlow<DataResource<PokemonInfoModel>>(
//        DataResource.loading())
//    val pokemonAppBarInfo = _pokemonAppBarInfo.asStateFlow()

    fun getPokemonInfo(pid: Int?) {
        viewModelScope.launch {
            getPokemonDetailUseCase(id = pid ?: 1).bind(
                state = _localAppBarInfoUiState,
                mapper = { it }
            )

        }
    }

    fun loadLocalInfo(pid: Int?) {
        viewModelScope.launch {
            getPokemonDetailLocalInfoUseCase(pid = pid ?: 1).bind (
                state = _localAppBarInfoUiState,
                mapper = { it.toPokemonDetailLocalInfoModel() }
            )
        }
    }

//    fun getAppBarInfo(pid: Int?) {
//        viewModelScope.launch {
//            getPokemonAppBarInfoUseCase(pid = pid ?: 1).bind (
//                state = _pokemonAppBarInfo,
//
//            )
//        }
//    }

}