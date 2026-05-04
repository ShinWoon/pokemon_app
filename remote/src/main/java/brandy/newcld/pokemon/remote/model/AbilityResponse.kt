package brandy.newcld.pokemon.remote.model

import brandy.newcld.pokemon.data.model.AbilityEntity
import brandy.newcld.pokemon.remote.RemoteMapper
import com.google.gson.annotations.SerializedName

data class AbilityResponse (
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("names") val names: List<AbilityNameResponse>
): RemoteMapper<AbilityEntity> {
    override fun toData(): AbilityEntity = AbilityEntity (
        id = id,
        engName = name,
        koName = names.firstOrNull{ it.language.name == "ko"}?.name ?: name
    )

}

data class AbilityNameResponse(
    @SerializedName("language") val language: NameUrlResponse,
    @SerializedName("name") val name: String,
)