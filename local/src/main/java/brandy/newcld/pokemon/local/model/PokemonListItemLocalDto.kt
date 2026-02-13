package brandy.newcld.pokemon.local.model

import androidx.room.ColumnInfo
import brandy.newcld.pokemon.data.model.PokemonListItemLocalEntity
import brandy.newcld.pokemon.local.LocalMapper

data class PokemonListItemLocalDto (
    @ColumnInfo("id")
    val pid: Int,
    @ColumnInfo("ko_name")
    val koName: String,
    @ColumnInfo("day_time_color")
    val dayTimeColor: String?,
    @ColumnInfo("night_time_color")
    val nightTimeColor: String?
): LocalMapper<PokemonListItemLocalEntity> {
    override fun toData(): PokemonListItemLocalEntity =
        PokemonListItemLocalEntity(pid = pid, koName = koName, dayTimeColor = dayTimeColor, nightTimeColor = nightTimeColor)
}