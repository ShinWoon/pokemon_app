package brandy.newcld.pokemon.presentation.model

import android.graphics.Color

data class PokemonAppBarInfoModel (
    val koName: String = "",
    val engName: String = "",
    val backgroundColor: Color,
    val imageUrl: String = "",
    val appBarIconUrl: String = "",
    val typeImageUrl: String = ""
)