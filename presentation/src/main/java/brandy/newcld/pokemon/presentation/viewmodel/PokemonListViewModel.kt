package brandy.newcld.pokemon.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import brandy.newcld.pokemon.domain.usecase.GetPokemonListUseCase
import brandy.newcld.pokemon.domain.usecase.UpdateBackgroundColorsUseCase
import brandy.newcld.pokemon.presentation.model.DayNight
import brandy.newcld.pokemon.presentation.model.PokemonListItemModel
import brandy.newcld.pokemon.presentation.model.UiState
import brandy.newcld.pokemon.presentation.model.toPokemonListItemModel
import brandy.newcld.pokemon.presentation.util.ColorUtil.toDarkerColor
import brandy.newcld.pokemon.presentation.util.ColorUtil.toPastelColor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val updateBackgroundColorsUseCase: UpdateBackgroundColorsUseCase
): BaseViewModel() {
    private val _pokemonList = MutableStateFlow<PagingData<PokemonListItemModel>>(PagingData.empty())
    val pokemonList = _pokemonList.asStateFlow()

    private val _updateState = MutableStateFlow(UiState<Int>())
    val updateState = _updateState.asStateFlow()

    private val _bgColors = MutableStateFlow<Map<Int, DayNight>>(emptyMap())
    val bgColors = _bgColors.asStateFlow()


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

    fun onColorExtracted(pid: Int, baseColor: Int) {
        viewModelScope.launch {
            val dayTimeColor = toPastelColor(baseColor)
            val nightTimeColor = toDarkerColor(baseColor)

            _bgColors.update { it + (pid to DayNight(dayTimeColor, nightTimeColor)) }

            updateBackgroundColorsUseCase(pid, dayTimeColor, nightTimeColor).bind(
                state = _updateState,
                mapper = { pid }
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