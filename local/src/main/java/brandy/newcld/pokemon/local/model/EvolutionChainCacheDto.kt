package brandy.newcld.pokemon.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import brandy.newcld.pokemon.data.model.EvolutionChainEntity
import brandy.newcld.pokemon.data.model.EvolutionStageEntity
import brandy.newcld.pokemon.local.room.RoomConstant.Table.EVOLUTION_CHAIN

@Entity(tableName = EVOLUTION_CHAIN)
data class EvolutionChainCacheDto(
    @PrimaryKey @ColumnInfo(name = "chain_id") val chainId: Int,
    @ColumnInfo(name = "stages_json") val stagesJson: String,
) {
    fun toEntity(): EvolutionChainEntity = EvolutionChainEntity(
        chainId = chainId,
        stages = decodeStages(stagesJson),
    )

    companion object {
        fun fromEntity(entity: EvolutionChainEntity): EvolutionChainCacheDto =
            EvolutionChainCacheDto(
                chainId = entity.chainId,
                stagesJson = encodeStages(entity.stages),
            )

        private const val STAGE_FIELD_DELIMITER = "|"
        private const val STAGE_DELIMITER = ";;"

        private fun encodeStages(stages: List<EvolutionStageEntity>): String =
            stages.joinToString(STAGE_DELIMITER) { stage ->
                listOf(
                    stage.speciesId.toString(),
                    stage.engName,
                    stage.koName,
                ).joinToString(STAGE_FIELD_DELIMITER)
            }

        private fun decodeStages(json: String): List<EvolutionStageEntity> {
            if (json.isBlank()) return emptyList()
            return json.split(STAGE_DELIMITER).mapNotNull { stage ->
                val parts = stage.split(STAGE_FIELD_DELIMITER)
                if (parts.size < 3) return@mapNotNull null
                EvolutionStageEntity(
                    speciesId = parts[0].toIntOrNull() ?: return@mapNotNull null,
                    engName = parts[1],
                    koName = parts[2],
                )
            }
        }
    }
}
