package brandy.newcld.pokemonapp.dao


import com.google.gson.annotations.SerializedName

data class Language(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)