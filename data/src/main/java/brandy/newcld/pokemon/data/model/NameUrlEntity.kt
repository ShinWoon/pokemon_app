package brandy.newcld.pokemon.data.model

import brandy.newcld.pokemon.data.DataMapper
import brandy.newcld.pokemon.domain.model.NameUrl

data class NameUrlEntity(
    val name: String,
    val url: String
): DataMapper<NameUrl> {
    override fun toDomain(): NameUrl = NameUrl(name, url)
}
