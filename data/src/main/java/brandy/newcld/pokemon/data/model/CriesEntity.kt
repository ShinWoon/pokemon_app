package brandy.newcld.pokemon.data.model

import brandy.newcld.pokemon.data.DataMapper
import brandy.newcld.pokemon.domain.model.Cries

data class CriesEntity(
    val latest: String,
    val legacy: String
): DataMapper<Cries> {
    override fun toDomain(): Cries = Cries(latest, legacy)
}
