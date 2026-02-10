package brandy.newcld.pokemon.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import brandy.newcld.pokemon.local.room.RoomConstant.Table.POKEMON

@Entity(tableName = POKEMON)
data class PokemonEntity (
    @PrimaryKey @ColumnInfo(name = "id") val pId: Int,
    @ColumnInfo(name = "eng_name") val engName: String?,
    @ColumnInfo(name = "ko_name") val koName: String?,
    @ColumnInfo(name = "day_time_color") val dayTimeColor: String?,
    @ColumnInfo(name = "night_time_color") val nightTimeColor: String?,
)