package brandy.newcld.pokemon.remote.model

import brandy.newcld.pokemon.data.model.HeldItemEntity
import brandy.newcld.pokemon.data.model.VersionDetailEntity
import brandy.newcld.pokemon.remote.RemoteMapper
import com.google.gson.annotations.SerializedName


data class VersionDetailResponse(
    @SerializedName("rarity") val rarity: Int,
    @SerializedName("version") val version: NameUrlResponse
): RemoteMapper<VersionDetailEntity> {
    override fun toData(): VersionDetailEntity =
       VersionDetailEntity(rarity, version.toData())
}