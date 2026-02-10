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
    @Query("SELECT id AS pid, ko_name AS koName FROM pokemon")
    fun getPokemonKoreanNameList(): Flow<List<PokemonKoreanNameDto>>

    @Query("SELECT * FROM pokemon WHERE id = :pid")
    fun getAll(pid: Int): Flow<PokemonEntity>

    @Query("SELECT day_time_color FROM pokemon WHERE id = :pid")
    fun getPokemonDayTimeColor(pid: Int): Flow<String>

    @Query("SELECT night_time_color FROM pokemon WHERE id = :pid")
    fun getPokemonNightTimeColor(pid: Int): Flow<String>

    @Query("UPDATE pokemon SET day_time_color = :dayTimeColor WHERE id = :pid")
    fun updateDayTimeColor(pid: Int, dayTimeColor: String)

    @Query("UPDATE pokemon SET night_time_color = :nightTimeColor WHERE id = :pid")
    fun updateNightTimeColor(pid: Int, nightTimeColor: String)
}