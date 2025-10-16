package brandy.newcld.pokemon.remote.model

import brandy.newcld.pokemon.data.model.HeldItemEntity
import brandy.newcld.pokemon.remote.RemoteMapper
import com.google.gson.annotations.SerializedName


data class VersionDetailResponse(
    @SerializedName("rarity") val rarity: Int,
    @SerializedName("version") val version: NameUrlResponse
): RemoteMapper<HeldItemEntity.VersionDetailEntity> {
    override fun toData(): HeldItemEntity.VersionDetailEntity =
       HeldItemEntity.VersionDetailEntity(rarity, version.toData())
}