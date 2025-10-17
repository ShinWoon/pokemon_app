package brandy.newcld.pokemon.data.repositoryImpl

import brandy.newcld.pokemon.data.bound.flowDataResource
import brandy.newcld.pokemon.data.remote.PokemonRemoteDataSource
import brandy.newcld.pokemon.dataresource.DataResource
import brandy.newcld.pokemon.domain.model.NameUrl
import brandy.newcld.pokemon.domain.model.PokemonInfo
import brandy.newcld.pokemon.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val remoteDataSource: PokemonRemoteDataSource
): PokemonRepository {
    override fun getPokemonList(
        limit: Int,
        offset: Int
    ): Flow<DataResource<List<NameUrl>>> = flowDataResource { remoteDataSource.getPokemonList(limit, offset) }

    override fun getPokemonInfo(id: Int): Flow<DataResource<PokemonInfo>> = flowDataResource { remoteDataSource.getPokemonInfo(id) }
}