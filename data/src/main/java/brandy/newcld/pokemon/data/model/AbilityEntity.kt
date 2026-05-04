package brandy.newcld.pokemon.data.model

import brandy.newcld.pokemon.data.DataMapper
import brandy.newcld.pokemon.domain.model.Ability

data class AbilityEntity (
    val id: Int,
    val engName: String,
    val koName: String,
): DataMapper<Ability> {
    override fun toDomain(): Ability = Ability(
        id = id,
        engName = engName,
        koName = koName,
    )
}