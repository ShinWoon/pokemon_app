package brandy.newcld.pokemon.data.local

import brandy.newcld.pokemon.data.model.PokemonAppBarEntity
import brandy.newcld.pokemon.data.model.PokemonKoreanNameEntity

interface PokemonLocalDataSource {
    suspend fun getPokemonAppBarInfo(pid: Int): PokemonAppBarEntity

    suspend fun getPokemonKoreanName(pid: Int): PokemonKoreanNameEntity

    suspend fun updateBackgroundColors(pid: Int, dayTimeColor: String, nightTimeColor: String)
}