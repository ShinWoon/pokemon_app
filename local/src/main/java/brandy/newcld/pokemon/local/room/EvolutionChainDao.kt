package brandy.newcld.pokemon.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import brandy.newcld.pokemon.local.model.EvolutionChainCacheDto
import brandy.newcld.pokemon.local.room.RoomConstant.Table.EVOLUTION_CHAIN

@Dao
interface EvolutionChainDao {
    @Query("SELECT * FROM $EVOLUTION_CHAIN WHERE chain_id = :chainId")
    suspend fun getById(chainId: Int): EvolutionChainCacheDto?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dto: EvolutionChainCacheDto)
}
