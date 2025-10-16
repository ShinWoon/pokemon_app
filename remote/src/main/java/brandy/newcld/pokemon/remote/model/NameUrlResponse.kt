package brandy.newcld.pokemon.remote.model


import brandy.newcld.pokemon.data.model.NameUrlEntity
import brandy.newcld.pokemon.remote.RemoteMapper
import com.google.gson.annotations.SerializedName

data class NameUrlResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
): RemoteMapper<NameUrlEntity> {
    override fun toData(): NameUrlEntity =
        NameUrlEntity(name, url)
}