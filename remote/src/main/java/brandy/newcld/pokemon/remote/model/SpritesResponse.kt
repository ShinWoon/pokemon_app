package brandy.newcld.pokemon.remote.model


import brandy.newcld.pokemon.data.model.OfficialArtworkEntity
import brandy.newcld.pokemon.data.model.OtherEntity
import brandy.newcld.pokemon.data.model.SpritesEntity
import brandy.newcld.pokemon.remote.RemoteMapper
import com.google.gson.annotations.SerializedName

data class SpritesResponse(
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("other")
    val other: OtherResponse
): RemoteMapper<SpritesEntity> {
    override fun toData(): SpritesEntity =
        SpritesEntity(frontDefault, other.toData())
}
data class OtherResponse(@SerializedName("official-artwork") val officialArtwork: OfficialArtworkResponse): RemoteMapper<OtherEntity> {
    override fun toData(): OtherEntity =
        OtherEntity(officialArtwork.toData())
}
data class OfficialArtworkResponse(@SerializedName("front_default") val frontDefault: String): RemoteMapper<OfficialArtworkEntity> {
    override fun toData(): OfficialArtworkEntity =
        OfficialArtworkEntity(frontDefault)
}