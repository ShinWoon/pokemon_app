package brandy.newcld.pokemon.remote.model

import brandy.newcld.pokemon.data.model.EvolutionChainEntity
import brandy.newcld.pokemon.data.model.EvolutionStageEntity
import brandy.newcld.pokemon.remote.RemoteMapper
import com.google.gson.annotations.SerializedName

data class EvolutionChainResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("chain") val chain: ChainLinkResponse,
) : RemoteMapper<EvolutionChainEntity> {
    override fun toData(): EvolutionChainEntity {
        val stages = mutableListOf<EvolutionStageEntity>()
        chain.collectStages(stages)
        return EvolutionChainEntity(chainId = id, stages = stages)
    }
}

data class ChainLinkResponse(
    @SerializedName("species") val species: NameUrlResponse,
    @SerializedName("evolves_to") val evolvesTo: List<ChainLinkResponse>,
) {
    fun collectStages(acc: MutableList<EvolutionStageEntity>, depth: Int = 0) {
        acc += EvolutionStageEntity(
            speciesId = extractSpeciesId(species.url),
            engName = species.name,
            koName = "",
            depth = depth,
        )
        evolvesTo.forEach { it.collectStages(acc, depth + 1) }
    }
}

private val SPECIES_ID_REGEX = Regex("/pokemon-species/(\\d+)/?$")

private fun extractSpeciesId(url: String): Int =
    SPECIES_ID_REGEX.find(url)?.groupValues?.getOrNull(1)?.toIntOrNull() ?: 0
