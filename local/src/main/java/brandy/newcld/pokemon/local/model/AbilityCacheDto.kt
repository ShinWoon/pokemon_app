package brandy.newcld.pokemon.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import brandy.newcld.pokemon.data.model.AbilityEntity
import brandy.newcld.pokemon.local.room.RoomConstant.Table.ABILITY

@Entity(tableName = ABILITY)
data class AbilityCacheDto (
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "ko_name") val koName: String,
) {
    fun toEntity(): AbilityEntity = AbilityEntity(id = 0, engName = name, koName = koName)
}