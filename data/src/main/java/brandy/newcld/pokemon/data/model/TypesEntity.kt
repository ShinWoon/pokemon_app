package brandy.newcld.pokemon.data.model

import brandy.newcld.pokemon.data.DataMapper
import brandy.newcld.pokemon.domain.model.Types

data class TypesEntity(
    val slot: Int,
    val type: NameUrlEntity
): DataMapper<Types> {
    override fun toDomain(): Types {
        val typeId = type.url.trimEnd('/').substringAfterLast('/')
        val imgUrl = if (typeId.isNotEmpty()) {
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/types/generation-viii/sword-shield/$typeId.png"
        } else ""
        return Types(slot, type.toDomain(), imgUrl)
    }
}