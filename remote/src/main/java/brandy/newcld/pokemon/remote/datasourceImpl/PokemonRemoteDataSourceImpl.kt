package brandy.newcld.pokemon.remote.datasourceImpl

import brandy.newcld.pokemon.data.model.NameUrlEntity
import brandy.newcld.pokemon.data.model.PokemonInfoEntity
import brandy.newcld.pokemon.data.remote.PokemonRemoteDataSource
import brandy.newcld.pokemon.remote.api.ApiService
import brandy.newcld.pokemon.remote.toData
import javax.inject.Inject

class PokemonRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
) : PokemonRemoteDataSource {
    override suspend fun getPokemonList(
        limit: Int,
        offset: Int
    ): List<NameUrlEntity> {
        return apiService.getPokemonList(limit = limit, offset = 0).results.toData()
    }

    override suspend fun getPokemonInfo(id: Int): PokemonInfoEntity {
        return apiService.getPokemonInfo(id = id).toData()
    }
}