package brandy.newcld.pokemon.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import brandy.newcld.pokemon.data.model.PokemonDetailLocalInfoEntity
import brandy.newcld.pokemon.local.LocalMapper
import brandy.newcld.pokemon.local.room.RoomConstant.Table.POKEMON

@Entity(tableName = POKEMON)
data class PokemonDetailLocalInfoEntity (
    @PrimaryKey @ColumnInfo(name = "id") val pId: Int,
    @ColumnInfo(name = "eng_name") val engName: String?,
    @ColumnInfo(name = "ko_name") val koName: String?,
    @ColumnInfo(name = "day_time_color") val dayTimeColor: String?,
    @ColumnInfo(name = "night_time_color") val nightTimeColor: String?,
): LocalMapper<PokemonDetailLocalInfoEntity> {
    override fun toData(): PokemonDetailLocalInfoEntity =
        PokemonDetailLocalInfoEntity(pId, koName ?: "", engName ?: "", dayTimeColor ?: "", nightTimeColor ?: "")
}