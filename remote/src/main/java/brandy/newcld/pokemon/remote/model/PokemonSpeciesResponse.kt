package brandy.newcld.pokemon.remote.model

import com.google.gson.annotations.SerializedName

data class PokemonSpeciesResponse(
    @SerializedName("flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntryResponse>,
    @SerializedName("evolution_chain")
    val evolutionChain: EvolutionChainRefResponse? = null,
) {
    fun getKoreanDescription(): String =
        flavorTextEntries
            .lastOrNull { it.language.name == "ko" }
            ?.flavorText
            ?.replace("\n", " ")
            ?.replace("", " ")
            ?: ""

    fun getEvolutionChainId(): Int? {
        val url = evolutionChain?.url ?: return null
        return CHAIN_ID_REGEX.find(url)?.groupValues?.getOrNull(1)?.toIntOrNull()
    }

    companion object {
        private val CHAIN_ID_REGEX = Regex("/evolution-chain/(\\d+)/?$")
    }
}

data class FlavorTextEntryResponse(
    @SerializedName("flavor_text")
    val flavorText: String,
    @SerializedName("language")
    val language: NameUrlResponse,
    @SerializedName("version")
    val version: NameUrlResponse
)

data class EvolutionChainRefResponse(
    @SerializedName("url") val url: String,
)
