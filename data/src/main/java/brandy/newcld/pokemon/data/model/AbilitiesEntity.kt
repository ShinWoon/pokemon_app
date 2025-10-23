package brandy.newcld.pokemon.data.model

import brandy.newcld.pokemon.data.DataMapper
import brandy.newcld.pokemon.domain.model.Abilities

data class AbilitiesEntity (
    val isHidden: Boolean,
    val slot: Int,
    val ability: NameUrlEntity
): DataMapper<Abilities> {
    override fun toDomain(): Abilities = Abilities(isHidden, slot, ability.toDomain())
}