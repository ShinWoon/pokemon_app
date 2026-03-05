package brandy.newcld.pokemon.data.model

import brandy.newcld.pokemon.data.DataMapper
import brandy.newcld.pokemon.domain.model.PokemonDetailLocalInfoItem

data class PokemonDetailLocalInfoEntity (
    val pid: Int,
    val koName: String,
    val engName: String,
    val dayTimeColor: String,
    val nightTimeColor: String
): DataMapper<PokemonDetailLocalInfoItem> {
    override fun toDomain(): PokemonDetailLocalInfoItem = PokemonDetailLocalInfoItem(
        pid = pid,
        koName = koName,
        engName = engName,
        dayTimeColor = dayTimeColor,
        nightTimeColor = nightTimeColor
    )
}