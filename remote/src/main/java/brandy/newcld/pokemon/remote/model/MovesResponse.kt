package brandy.newcld.pokemon.remote.model


import brandy.newcld.pokemon.data.model.MovesEntity
import brandy.newcld.pokemon.remote.RemoteMapper
import com.google.gson.annotations.SerializedName

data class MovesResponse(
    @SerializedName("move")
    val move: NameUrlResponse
): RemoteMapper<MovesEntity> {
    override fun toData(): MovesEntity =
        MovesEntity(move.toData())
}