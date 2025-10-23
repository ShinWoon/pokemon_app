package brandy.newcld.pokemon.remote.model


import brandy.newcld.pokemon.data.model.HeldItemEntity
import brandy.newcld.pokemon.remote.RemoteMapper
import brandy.newcld.pokemon.remote.toData
import com.google.gson.annotations.SerializedName

data class HeldItemResponse(
    @SerializedName("item")
    val item: NameUrlResponse,
    @SerializedName("version_details")
    val versionDetails: List<VersionDetailResponse>
): RemoteMapper<HeldItemEntity> {
    override fun toData(): HeldItemEntity =
        HeldItemEntity(item.toData(), versionDetails.toData())
}