package brandy.newcld.pokemon.data.repositoryImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import brandy.newcld.pokemon.data.bound.flowDataResource
import brandy.newcld.pokemon.data.remote.PokemonListPagingSource
import brandy.newcld.pokemon.data.remote.PokemonRemoteDataSource
import brandy.newcld.pokemon.data.toDomainModel
import brandy.newcld.pokemon.dataresource.DataResource
import brandy.newcld.pokemon.domain.model.NameUrl
import brandy.newcld.pokemon.domain.model.PokemonInfo
import brandy.newcld.pokemon.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val remoteDataSource: PokemonRemoteDataSource
): PokemonRepository {
    override fun getPokemonInfo(id: Int): Flow<DataResource<PokemonInfo>> = flowDataResource { remoteDataSource.getPokemonInfo(id) }

    override fun getPokemonList(
        limit: Int,
        offset: Int
    ): Flow<PagingData<List<NameUrl>>> = Pager(
        config = PagingConfig (
            pageSize = 20,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { PokemonListPagingSource(remoteDataSource) }
    ).flow.toDomainModel()
}