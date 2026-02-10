package brandy.newcld.pokemon.local.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class PokemonKoreanNameEntity (
    @PrimaryKey @ColumnInfo(name = "id") val pId: Int,
    @ColumnInfo(name = "ko_name") val koName: String?,
)