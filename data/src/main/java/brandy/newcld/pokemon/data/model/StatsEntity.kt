package brandy.newcld.pokemon.data.model

import brandy.newcld.pokemon.data.DataMapper
import brandy.newcld.pokemon.domain.model.Stats

data class StatsEntity(
    val baseStat: Int,
    val effort: Int,
    val stat: NameUrlEntity
): DataMapper<Stats> {
    override fun toDomain(): Stats = Stats(baseStat, effort, stat.toDomain())
}
