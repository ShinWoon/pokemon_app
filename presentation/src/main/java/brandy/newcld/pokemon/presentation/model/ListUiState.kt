package brandy.newcld.pokemon.presentation.model

data class ListUiState<T> (
    val isLoading: Boolean = false,
    val items: List<T> = emptyList(),
    val error: Throwable? = null
)