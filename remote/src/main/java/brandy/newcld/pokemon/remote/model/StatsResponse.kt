package brandy.newcld.pokemon.remote.model


import com.google.gson.annotations.SerializedName

data class StatsResponse(
    @SerializedName("base_stat")
    val baseStat: Int,
    @SerializedName("effort")
    val effort: Int,
    @SerializedName("stat")
    val stat: NameUrlResponse
)