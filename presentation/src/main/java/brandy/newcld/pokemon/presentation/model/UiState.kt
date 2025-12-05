package brandy.newcld.pokemon.presentation.model

data class UiState<T> (
    val isLoading: Boolean = false,
    val data: T? = null,
    val error: Throwable? = null
)