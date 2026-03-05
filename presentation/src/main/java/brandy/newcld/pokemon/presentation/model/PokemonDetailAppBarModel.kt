package brandy.newcld.pokemon.presentation.model

data class PokemonDetailAppBarModel (
    val pid: Int = 0,
    val koName: String = "",
    val engName: String = "",
    val dayTimeColor: Int = 0,
    val nightTimeColor: Int = 0,
    val imgUrl: String = "",
    val typeImgUrl: String = "",
    val iconImgUrl: String = "",
)