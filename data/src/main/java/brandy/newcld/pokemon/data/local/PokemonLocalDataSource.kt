package brandy.newcld.pokemon.data.local

import androidx.paging.PagingData
import brandy.newcld.pokemon.data.model.PokemonDetailLocalInfoEntity
import brandy.newcld.pokemon.data.model.PokemonListItemLocalEntity
import kotlinx.coroutines.flow.Flow

interface PokemonLocalDataSource {
    suspend fun getPokemonDetailLocalInfo(pid: Int): PokemonDetailLocalInfoEntity

    fun getLocalPaging(): Flow<PagingData<PokemonListItemLocalEntity>>

    suspend fun updateBackgroundColors(pid: Int, dayTimeColor: String, nightTimeColor: String)
}