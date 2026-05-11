package brandy.newcld.pokemon.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import brandy.newcld.pokemon.domain.usecase.GetAllLocalUseCase
import brandy.newcld.pokemon.domain.usecase.GetPokemonListUseCase
import brandy.newcld.pokemon.domain.usecase.UpdateBackgroundColorsUseCase
import brandy.newcld.pokemon.presentation.model.DayNight
import brandy.newcld.pokemon.presentation.model.PokemonItemLocalModel
import brandy.newcld.pokemon.presentation.model.PokemonListItemModel
import brandy.newcld.pokemon.presentation.model.SearchResultItem
import brandy.newcld.pokemon.presentation.model.UiState
import brandy.newcld.pokemon.presentation.model.toPokemonItemLocalModel
import brandy.newcld.pokemon.presentation.model.toPokemonListItemModel
import brandy.newcld.pokemon.presentation.model.toSearchResultItem
import brandy.newcld.pokemon.presentation.util.ColorUtil.toDarkerColor
import brandy.newcld.pokemon.presentation.util.ColorUtil.toPastelColor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val getAllLocalUseCase: GetAllLocalUseCase,
    private val updateBackgroundColorsUseCase: UpdateBackgroundColorsUseCase
): BaseViewModel() {
    val localById: StateFlow<Map<Int, PokemonItemLocalModel>> =
        getAllLocalUseCase()
            .map { list -> list.associate { it.pid to it.toPokemonItemLocalModel() } }
            .stateIn(viewModelScope, SharingStarted.Eagerly, emptyMap())

    private val localIds: StateFlow<Set<Int>> =
        getAllLocalUseCase()
            .map { list -> list.map { it.pid }.toSet() }
            .distinctUntilChanged()
            .stateIn(viewModelScope, SharingStarted.Eagerly, emptySet())

    val pokemonList: Flow<PagingData<PokemonListItemModel>> =
        combine(
            getPokemonListUseCase()
                .map { pagingData -> pagingData.map { it.toPokemonListItemModel() } }
                .cachedIn(viewModelScope),
            localIds,
        ) { paging, ids ->
            if (ids.isEmpty()) paging
            else paging.filter { it.id in ids }
        }

    private val _updateState = MutableStateFlow(UiState<Int>())
    val updateState = _updateState.asStateFlow()

    private val _bgColors = MutableStateFlow<Map<Int, DayNight>>(emptyMap())
    val bgColors = _bgColors.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    val searchResults: StateFlow<List<SearchResultItem>> = combine(
        _searchQuery,
        localById,
    ) { query, locals ->
        val trimmed = query.trim()
        if (trimmed.isBlank()) {
            emptyList()
        } else {
            locals.values
                .filter { local ->
                    local.koName.contains(trimmed, ignoreCase = true) ||
                        local.id.toString().contains(trimmed)
                }
                .sortedBy { it.id }
                .map { it.toSearchResultItem() }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun setSearchActive(active: Boolean) {
        _isSearching.value = active
        if (!active) _searchQuery.value = ""
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
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
