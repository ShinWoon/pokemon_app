package brandy.newcld.pokemon.presentation.model

import brandy.newcld.pokemon.dataresource.AppError

data class ListUiState<T> (
    val isLoading: Boolean = false,
    val items: List<T> = emptyList(),
    val error: AppError? = null
)
