package brandy.newcld.pokemon.data.model

import brandy.newcld.pokemon.data.DataMapper
import brandy.newcld.pokemon.domain.model.EvolutionChain
import brandy.newcld.pokemon.domain.model.EvolutionStage

data class EvolutionChainEntity(
    val chainId: Int,
    val stages: List<EvolutionStageEntity>,
) : DataMapper<EvolutionChain> {
    override fun toDomain(): EvolutionChain = EvolutionChain(
        chainId = chainId,
        stages = stages.map(EvolutionStageEntity::toDomain),
    )
}

data class EvolutionStageEntity(
    val speciesId: Int,
    val engName: String,
    val koName: String,
    val depth: Int,
) : DataMapper<EvolutionStage> {
    override fun toDomain(): EvolutionStage = EvolutionStage(
        speciesId = speciesId,
        engName = engName,
        koName = koName,
        depth = depth,
    )
}
