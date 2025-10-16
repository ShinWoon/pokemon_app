package brandy.newcld.pokemon.remote.model


import brandy.newcld.pokemon.data.model.TypesEntity
import brandy.newcld.pokemon.remote.RemoteMapper
import com.google.gson.annotations.SerializedName

data class TypesResponse(
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("type")
    val type: NameUrlResponse
): RemoteMapper<TypesEntity> {
    override fun toData(): TypesEntity =
        TypesEntity(slot, type.toData())
}