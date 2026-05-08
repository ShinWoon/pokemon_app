package brandy.newcld.pokemon.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import brandy.newcld.pokemon.local.model.AbilityCacheDto
import brandy.newcld.pokemon.local.room.RoomConstant.Table.ABILITY

@Dao
interface AbilityDao {
    @Query("SELECT * FROM $ABILITY WHERE name = :name")
    suspend fun getByName(name: String): AbilityCacheDto?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dto: AbilityCacheDto)
}
