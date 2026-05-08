package brandy.newcld.pokemon.presentation.model

import brandy.newcld.pokemon.dataresource.AppError

data class UiState<T> (
    val isLoading: Boolean = false,
    val data: T? = null,
    val error: AppError? = null
)
