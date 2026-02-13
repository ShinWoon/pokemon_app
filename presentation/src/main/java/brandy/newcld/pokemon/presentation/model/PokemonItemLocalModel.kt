package brandy.newcld.pokemon.presentation.model

import brandy.newcld.pokemon.domain.model.PokemonListItemLocal

data class PokemonItemLocalModel (
    val id: Int = 0,
    val koName: String = "",
    val dayTimeColor: Int = 0,
    val nightTimeColor: Int = 0
)

fun PokemonListItemLocal.toPokemonItemLocalModel(): PokemonItemLocalModel {
    return PokemonItemLocalModel(
        id = pid,
        koName = koName,
        dayTimeColor = dayTimeColor?.toInt() ?: 0,
        nightTimeColor = nightTimeColor?.toInt() ?: 0
    )
}