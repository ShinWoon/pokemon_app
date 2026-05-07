package brandy.newcld.pokemon.presentation.model

import brandy.newcld.pokemon.domain.model.EvolutionChain

data class EvolutionChainModel(
    val chainId: Int = 0,
    val stages: List<EvolutionStageModel> = emptyList(),
)

data class EvolutionStageModel(
    val speciesId: Int,
    val displayName: String,
    val spriteUrl: String,
    val depth: Int,
)

private const val OFFICIAL_ARTWORK_BASE =
    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork"

fun EvolutionChain.toPresentationModel(): EvolutionChainModel = EvolutionChainModel(
    chainId = chainId,
    stages = stages.map { stage ->
        EvolutionStageModel(
            speciesId = stage.speciesId,
            displayName = stage.koName.ifBlank { stage.engName },
            spriteUrl = "$OFFICIAL_ARTWORK_BASE/${stage.speciesId}.png",
            depth = stage.depth,
        )
    },
)
