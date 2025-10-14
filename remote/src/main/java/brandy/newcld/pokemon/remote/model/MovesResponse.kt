package brandy.newcld.pokemon.remote.model


import com.google.gson.annotations.SerializedName

data class MovesResponse(
    @SerializedName("move")
    val move: NameUrlResponse
)