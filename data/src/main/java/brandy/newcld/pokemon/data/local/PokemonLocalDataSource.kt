package brandy.newcld.pokemon.data.local

import androidx.paging.PagingSource
import brandy.newcld.pokemon.data.model.PokemonAppBarEntity
import brandy.newcld.pokemon.data.model.PokemonListItemLocalEntity

interface PokemonLocalDataSource {
    suspend fun getPokemonAppBarInfo(pid: Int): PokemonAppBarEntity

    fun getLocalPaging(): PagingSource<Int, PokemonListItemLocalEntity>

    suspend fun updateBackgroundColors(pid: Int, dayTimeColor: String, nightTimeColor: String)
}