package brandy.newcld.pokemon.domain.model

data class Sprites(
    val frontDefault: String,
    val other: Other
)
data class Other (val officialArtwork: OfficialArtwork)
data class OfficialArtwork(val frontDefault: String)