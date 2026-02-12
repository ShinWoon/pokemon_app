package brandy.newcld.pokemon.local.model

import androidx.room.ColumnInfo

data class PokemonListItemLocalDto (
    @ColumnInfo("id")
    val pid: Int,
    @ColumnInfo("ko_name")
    val koName: String,
    @ColumnInfo("day_time_color")
    val dayTimeColor: String?,
    @ColumnInfo("night_time_color")
    val nightTimeColor: String?
)