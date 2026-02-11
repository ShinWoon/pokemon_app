package brandy.newcld.pokemon.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import brandy.newcld.pokemon.local.model.PokemonEntity
import brandy.newcld.pokemon.local.model.PokemonKoreanNameDto
import brandy.newcld.pokemon.local.model.PokemonRoomInfoDto
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Query("SELECT id AS pid, ko_name AS koName FROM ${RoomConstant.ROOM_DB_NAME}")
    fun getPokemonKoreanNameList(): Flow<List<PokemonKoreanNameDto>>

    @Query("SELECT * FROM ${RoomConstant.ROOM_DB_NAME} WHERE id = :pid")
    fun getAll(pid: Int): Flow<PokemonEntity>

    @Query("SELECT day_time_color FROM ${RoomConstant.ROOM_DB_NAME} WHERE id = :pid")
    fun getPokemonDayTimeColor(pid: Int): Flow<String>

    @Query("SELECT night_time_color FROM ${RoomConstant.ROOM_DB_NAME} WHERE id = :pid")
    fun getPokemonNightTimeColor(pid: Int): Flow<String>

    @Query("UPDATE ${RoomConstant.ROOM_DB_NAME} SET day_time_color = :dayTimeColor, night_time_color = :nightTimeColor WHERE id = :pid")
    fun updateColor(pid: Int, dayTimeColor: String, nightTimeColor: String)

    @Query("SELECT COUNT(*) FROM pokemon")
    suspend fun count(): Int
}