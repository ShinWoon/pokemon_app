package brandy.newcld.pokemon.presentation.model

import brandy.newcld.pokemon.domain.model.PokemonDetailLocalInfoItem

data class PokemonDetailLocalInfoModel (
    val pid: Int = 0,
    val koName: String = "",
    val engName: String = "",
    val dayTimeColor: Int = 0,
    val nightTimeColor: Int = 0
)


fun PokemonDetailLocalInfoItem.toPokemonDetailLocalInfoModel(): PokemonDetailLocalInfoModel =
    PokemonDetailLocalInfoModel(
        pid = pid,
        koName = koName,
        engName = engName,
        dayTimeColor = dayTimeColor?.toIntOrNull() ?: 0,
        nightTimeColor = nightTimeColor?.toIntOrNull() ?: 0,
    )