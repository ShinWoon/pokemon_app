package brandy.newcld.pokemon.remote.model


import brandy.newcld.pokemon.data.model.AbilitiesEntity
import brandy.newcld.pokemon.remote.RemoteMapper
import com.google.gson.annotations.SerializedName

data class AbilitiesResponse(
    @SerializedName("is_hidden")
    val isHidden: Boolean,
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("ability")
    val ability: NameUrlResponse
): RemoteMapper<AbilitiesEntity> {
    override fun toData(): AbilitiesEntity =
        AbilitiesEntity(isHidden, slot, ability.toData())
}