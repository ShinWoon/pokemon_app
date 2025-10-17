package brandy.newcld.pokemon.data.remote

import brandy.newcld.pokemon.data.model.NameUrlEntity
import brandy.newcld.pokemon.data.model.PokemonInfoEntity

interface PokemonRemoteDataSource {
    suspend fun getPokemonList(limit: Int, offset: Int): List<NameUrlEntity>

    suspend fun getPokemonInfo(id: Int): PokemonInfoEntity
}