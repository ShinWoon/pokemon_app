package brandy.newcld.pokemon.remote.api

import brandy.newcld.pokemon.remote.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("pokemon/{id}")
    suspend fun getPokemon(
        @Path("id") id: Int
    ): PokemonResponse


}