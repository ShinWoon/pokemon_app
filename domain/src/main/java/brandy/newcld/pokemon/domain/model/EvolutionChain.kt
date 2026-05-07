package brandy.newcld.pokemon.domain.model

data class EvolutionChain(
    val chainId: Int,
    val stages: List<EvolutionStage>,
)

data class EvolutionStage(
    val speciesId: Int,
    val engName: String,
    val koName: String,
    val depth: Int,
)
