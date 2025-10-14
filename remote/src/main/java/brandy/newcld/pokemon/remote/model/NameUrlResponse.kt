package brandy.newcld.pokemon.remote.model


import com.google.gson.annotations.SerializedName

data class NameUrlResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)