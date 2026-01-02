package brandy.newcld.pokemon.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class PokemonEntity (
    @PrimaryKey val pId: Int,
    @ColumnInfo(name = "eng_name") val engName: String?,
    @ColumnInfo(name = "ko_name") val koName: String?,
    @ColumnInfo(name = "day_time_color") val dayTimeColor: String?,
    @ColumnInfo(name = "night_time_color") val nightTimeColor: String?,
)