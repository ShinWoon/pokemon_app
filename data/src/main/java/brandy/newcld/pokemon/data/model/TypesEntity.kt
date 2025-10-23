package brandy.newcld.pokemon.data.model

import brandy.newcld.pokemon.data.DataMapper
import brandy.newcld.pokemon.domain.model.Types

data class TypesEntity(
    val slot: Int,
    val type: NameUrlEntity
): DataMapper<Types> {
    override fun toDomain(): Types = Types(slot, type.toDomain())
}