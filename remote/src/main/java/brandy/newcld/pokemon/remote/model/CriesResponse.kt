package brandy.newcld.pokemon.remote.model


import com.google.gson.annotations.SerializedName

data class CriesResponse(
    @SerializedName("latest")
    val latest: String,
    @SerializedName("legacy")
    val legacy: String
)