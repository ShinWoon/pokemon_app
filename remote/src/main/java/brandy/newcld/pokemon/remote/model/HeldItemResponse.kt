package brandy.newcld.pokemon.remote.model


import com.google.gson.annotations.SerializedName

data class HeldItemResponse(
    @SerializedName("item")
    val item: NameUrlResponse,
    @SerializedName("version_details")
    val versionDetails: List<VersionDetail>
) {
    data class VersionDetail(@SerializedName("rarity") val rarity: Int, @SerializedName("version") val version: NameUrlResponse)
}