package brandy.newcld.pokemon.remote.api

import brandy.newcld.pokemon.remote.model.PokemonInfoResponse
import brandy.newcld.pokemon.remote.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    /* 포켓몬 리스트 호출 */
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonResponse

    /* 선택 포켓몬 상세 정보 호출 */
    @GET("pokemon/{id}")
    suspend fun getPokemonInfo(
        @Path("id") id: Int
    ): PokemonInfoResponse
}