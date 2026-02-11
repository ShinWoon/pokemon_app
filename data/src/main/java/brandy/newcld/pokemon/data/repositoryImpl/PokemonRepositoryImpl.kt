package brandy.newcld.pokemon.data.repositoryImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import brandy.newcld.pokemon.data.bound.flowDataResource
import brandy.newcld.pokemon.data.local.PokemonLocalDataSource
import brandy.newcld.pokemon.data.remote.PokemonListPagingSource
import brandy.newcld.pokemon.data.remote.PokemonRemoteDataSource
import brandy.newcld.pokemon.data.toDomainPaging
import brandy.newcld.pokemon.dataresource.DataResource
import brandy.newcld.pokemon.domain.model.NameUrl
import brandy.newcld.pokemon.domain.model.PokemonInfo
import brandy.newcld.pokemon.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val remoteDataSource: PokemonRemoteDataSource,
    private val localDataSource: PokemonLocalDataSource
): PokemonRepository {
    override fun getPokemonInfo(id: Int): Flow<DataResource<PokemonInfo>> = flowDataResource { remoteDataSource.getPokemonInfo(id) }

    override fun getPokemonList(): Flow<PagingData<NameUrl>> = Pager(
        config = PagingConfig (
            pageSize = 40,
            initialLoadSize = 60,
            prefetchDistance = 30,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { PokemonListPagingSource(remoteDataSource) }
    ).flow.map { it.toDomainPaging() }

    override fun updateBackgroundColors(
        pid: Int,
        dayTimeColor: String,
        nightTimeColor: String
    ): Flow<DataResource<Unit>> = flowDataResource { localDataSource.updateBackgroundColors(pid, dayTimeColor, nightTimeColor) }
}