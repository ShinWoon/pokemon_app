package brandy.newcld.pokemon.data.local

import androidx.paging.PagingData
import brandy.newcld.pokemon.data.model.AbilityEntity
import brandy.newcld.pokemon.data.model.EvolutionChainEntity
import brandy.newcld.pokemon.data.model.PokemonDetailLocalInfoEntity
import brandy.newcld.pokemon.data.model.PokemonListItemLocalEntity
import kotlinx.coroutines.flow.Flow

interface PokemonLocalDataSource {
    suspend fun getPokemonDetailLocalInfo(pid: Int): PokemonDetailLocalInfoEntity

    fun getAllLocal(): Flow<List<PokemonListItemLocalEntity>>

    suspend fun updateBackgroundColors(pid: Int, dayTimeColor: String, nightTimeColor: String)

    suspend fun getAbility(name: String): AbilityEntity?

    suspend fun saveAbility(entity: AbilityEntity)

    suspend fun getEvolutionChain(chainId: Int): EvolutionChainEntity?

    suspend fun saveEvolutionChain(entity: EvolutionChainEntity)

    suspend fun getKoNameByPokemonId(pid: Int): String?
}