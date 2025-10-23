package brandy.newcld.pokemon.remote.model


import brandy.newcld.pokemon.data.model.PokemonEntity
import brandy.newcld.pokemon.remote.RemoteMapper
import brandy.newcld.pokemon.remote.toData
import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: List<NameUrlResponse>
): RemoteMapper<PokemonEntity> {
    override fun toData(): PokemonEntity =
        PokemonEntity(count, next, previous, results.toData())
}