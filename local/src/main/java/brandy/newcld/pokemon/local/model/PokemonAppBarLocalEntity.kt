package brandy.newcld.pokemon.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import brandy.newcld.pokemon.data.model.PokemonAppBarEntity
import brandy.newcld.pokemon.data.model.PokemonListItemLocalEntity
import brandy.newcld.pokemon.local.LocalMapper
import brandy.newcld.pokemon.local.room.RoomConstant.Table.POKEMON

@Entity(tableName = POKEMON)
data class PokemonAppBarLocalEntity (
    @PrimaryKey @ColumnInfo(name = "id") val pId: Int,
    @ColumnInfo(name = "eng_name") val engName: String?,
    @ColumnInfo(name = "ko_name") val koName: String?,
    @ColumnInfo(name = "day_time_color") val dayTimeColor: String?,
    @ColumnInfo(name = "night_time_color") val nightTimeColor: String?,
): LocalMapper<PokemonAppBarEntity> {
    override fun toData(): PokemonAppBarEntity =
        PokemonAppBarEntity(pId, koName ?: "", engName ?: "", dayTimeColor ?: "", nightTimeColor ?: "")
}