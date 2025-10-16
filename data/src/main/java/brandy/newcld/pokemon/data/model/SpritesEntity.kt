package brandy.newcld.pokemon.data.model

data class SpritesEntity(
    val frontDefault: String,
    val other: OtherEntity
) {
    data class OtherEntity (val officialArtwork: OfficialArtworkEntity)
    data class OfficialArtworkEntity(val frontDefault: String)
}