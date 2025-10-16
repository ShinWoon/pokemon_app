package brandy.newcld.pokemon.remote.datasourceImpl

import brandy.newcld.pokemon.data.model.NameUrlEntity
import brandy.newcld.pokemon.data.model.PokemonInfoEntity
import brandy.newcld.pokemon.data.remote.RemoteDataSource
import brandy.newcld.pokemon.remote.api.ApiService
import brandy.newcld.pokemon.remote.toData
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
) : RemoteDataSource {
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