package brandy.newcld.pokemon.remote.model


import brandy.newcld.pokemon.data.model.CriesEntity
import brandy.newcld.pokemon.remote.RemoteMapper
import com.google.gson.annotations.SerializedName

data class CriesResponse(
    @SerializedName("latest")
    val latest: String,
    @SerializedName("legacy")
    val legacy: String
): RemoteMapper<CriesEntity> {
    override fun toData(): CriesEntity =
        CriesEntity(latest, legacy)
}