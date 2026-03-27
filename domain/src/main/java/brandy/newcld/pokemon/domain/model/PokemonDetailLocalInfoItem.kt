package brandy.newcld.pokemon.domain.model

data class PokemonDetailLocalInfoItem (
    val pid: Int,
    val engName: String,
    val koName: String,
    val dayTimeColor: String?,
    val nightTimeColor: String?
)