package brandy.newcld.pokemon.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import brandy.newcld.pokemon.local.model.PokemonKoreanName

@Dao
interface PokemonDao {
    @Query("SELECT pid, ko_name FROM pokemon")
    fun getPokemonKoreanNameList(): List<PokemonKoreanName>

    @Query("SELECT day_time_color FROM pokemon WHERE pid = :pid")
    fun getPokemonDayTimeColor(pid: Int): String

    @Query("SELECT night_time_color FROM pokemon WHERE pid = :pid")
    fun getPokemonNightTimeColor(pid: Int): String

    @Insert
    fun insertDayTimeColor(pid: Int, dayTimeColor: String)

    @Insert
    fun insertNightTimeColor(pid: Int, nightTimeColor: String)
}