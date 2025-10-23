package brandy.newcld.pokemon.remote.model


import brandy.newcld.pokemon.data.model.StatsEntity
import brandy.newcld.pokemon.remote.RemoteMapper
import com.google.gson.annotations.SerializedName

data class StatsResponse(
    @SerializedName("base_stat")
    val baseStat: Int,
    @SerializedName("effort")
    val effort: Int,
    @SerializedName("stat")
    val stat: NameUrlResponse
): RemoteMapper<StatsEntity> {
    override fun toData(): StatsEntity =
        StatsEntity(baseStat, effort, stat.toData())
}