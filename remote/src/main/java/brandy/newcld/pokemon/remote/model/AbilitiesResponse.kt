package brandy.newcld.pokemon.remote.model


import com.google.gson.annotations.SerializedName

data class AbilitiesResponse(
    @SerializedName("is_hidden")
    val isHidden: Boolean,
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("ability")
    val ability: NameUrlResponse
)