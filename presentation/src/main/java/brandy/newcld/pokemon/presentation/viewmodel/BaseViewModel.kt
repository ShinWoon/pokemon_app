package brandy.newcld.pokemon.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import brandy.newcld.pokemon.dataresource.DataResource
import brandy.newcld.pokemon.presentation.model.ListUiState
import brandy.newcld.pokemon.presentation.model.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

private const val TAG = "BaseViewModel"

abstract class BaseViewModel: ViewModel() {
    protected fun <D, U> Flow<DataResource<D>>.bind(
        state: MutableStateFlow<UiState<U>>,
        mapper: (D) -> U,
    ) {
        this.onEach { res ->
            state.update { current ->
                when(res) {
                    is DataResource.Loading -> current.copy(isLoading = true)
                    is DataResource.Success -> current.copy(
                        isLoading = false,
                        data = mapper(res.data),
                        error = null
                    )
                    is DataResource.Error -> current.copy(
                        isLoading = false,
                        error = res.throwable
                    )
                }
            }
        }.flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }

    protected fun <D, U> Flow<DataResource<List<D>>>.bindList(
        state: MutableStateFlow<ListUiState<U>>,
        mapper: (D) -> U,
    ) {
        this.onEach { res ->
            state.update { current ->
                when(res) {
                    is DataResource.Loading -> current.copy(isLoading = true)
                    is DataResource.Success -> current.copy(
                        isLoading = false,
                        items = res.data.map(mapper),
                        error = null
                    )
                    is DataResource.Error -> current.copy(
                        isLoading = false,
                        error = res.throwable
                    )
                }
            }
        }.flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }

    /**
     * DataResource 끼리 합치는 함수
     */
    protected fun <A, B, R> combineDataResource(
        fa: Flow<DataResource<A>>,
        fb: Flow<DataResource<B>>,
        combiner: (A, B) -> R
    ): Flow<DataResource<R>> =
        combine(fa, fb) { ra, rb ->
            when {
                ra is DataResource.Error -> DataResource.Error(ra.throwable)
                rb is DataResource.Error -> DataResource.Error(rb.throwable)
                ra is DataResource.Loading || rb is DataResource.Loading -> DataResource.Loading()
                ra is DataResource.Success && rb is DataResource.Success ->
                    DataResource.Success(combiner(ra.data, rb.data))
                else -> DataResource.Loading() // 안전망
            }
        }
}