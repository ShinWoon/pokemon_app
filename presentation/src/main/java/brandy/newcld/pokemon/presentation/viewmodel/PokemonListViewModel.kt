package brandy.newcld.pokemon.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import brandy.newcld.pokemon.domain.usecase.GetPokemonListUseCase
import brandy.newcld.pokemon.domain.usecase.UpdateBackgroundColorsUseCase
import brandy.newcld.pokemon.presentation.model.PokemonListItemModel
import brandy.newcld.pokemon.presentation.model.UiState
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
    private val updateBackgroundColorsUseCase: UpdateBackgroundColorsUseCase
): BaseViewModel() {
    private val _pokemonList = MutableStateFlow<PagingData<PokemonListItemModel>>(PagingData.empty())
    val pokemonList = _pokemonList.asStateFlow()

    private val _updateState = MutableStateFlow(UiState<Boolean>())
    val updateState = _updateState.asStateFlow()

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

    fun updateBackgroundColors(pid: Int, dayTimeColor: String, nightTimeColor: String) {
        viewModelScope.launch {
            updateBackgroundColorsUseCase(pid, dayTimeColor, nightTimeColor).bind(
                state = _updateState,
                mapper = { true }
            )
        }
    }

    /**
     * 색상 업데이트 초기화 함수
     */
    fun consumeUpdateResult() {
        _updateState.value = UiState()
    }
}