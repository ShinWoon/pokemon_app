package brandy.newcld.pokemon.local.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import brandy.newcld.pokemon.local.model.PokemonEntity
import brandy.newcld.pokemon.local.model.PokemonListItemLocalDto
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Query("SELECT * FROM ${RoomConstant.ROOM_DB_NAME} ORDER BY id")
    fun getLocalPaging(): PagingSource<Int, PokemonListItemLocalDto>

    @Query("SELECT * FROM ${RoomConstant.ROOM_DB_NAME} WHERE id = :pid")
    fun getAll(pid: Int): Flow<PokemonEntity>

    @Query("UPDATE ${RoomConstant.ROOM_DB_NAME} SET day_time_color = :dayTimeColor, night_time_color = :nightTimeColor WHERE id = :pid")
    fun updateColor(pid: Int, dayTimeColor: String, nightTimeColor: String)
}