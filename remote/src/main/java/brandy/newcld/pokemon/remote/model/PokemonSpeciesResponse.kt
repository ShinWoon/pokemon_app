package brandy.newcld.pokemon.remote.model

import com.google.gson.annotations.SerializedName

data class PokemonSpeciesResponse(
    @SerializedName("flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntryResponse>
) {
    fun getKoreanDescription(): String =
        flavorTextEntries
            .lastOrNull { it.language.name == "ko" }
            ?.flavorText
            ?.replace("\n", " ")
            ?.replace("\u000c", " ")
            ?: ""
}

data class FlavorTextEntryResponse(
    @SerializedName("flavor_text")
    val flavorText: String,
    @SerializedName("language")
    val language: NameUrlResponse,
    @SerializedName("version")
    val version: NameUrlResponse
)
