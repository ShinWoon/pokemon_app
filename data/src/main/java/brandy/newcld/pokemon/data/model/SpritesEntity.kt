package brandy.newcld.pokemon.data.model

import brandy.newcld.pokemon.data.DataMapper
import brandy.newcld.pokemon.domain.model.OfficialArtwork
import brandy.newcld.pokemon.domain.model.Other
import brandy.newcld.pokemon.domain.model.Sprites

data class SpritesEntity(
    val frontDefault: String,
    val other: OtherEntity
): DataMapper<Sprites> {
    override fun toDomain(): Sprites = Sprites(frontDefault, other.toDomain())
}
data class OtherEntity (val officialArtwork: OfficialArtworkEntity): DataMapper<Other> {
    override fun toDomain(): Other = Other(officialArtwork.toDomain())
}
data class OfficialArtworkEntity(val frontDefault: String): DataMapper<OfficialArtwork> {
    override fun toDomain(): OfficialArtwork = OfficialArtwork(frontDefault)
}
