package brandy.newcld.pokemon.data.model

import brandy.newcld.pokemon.data.DataMapper
import brandy.newcld.pokemon.domain.model.PokemonListItemLocal

data class PokemonListItemLocalEntity (
    val pid: Int,
    val koName: String,
    val dayTimeColor: String?,
    val nightTimeColor: String?
): DataMapper<PokemonListItemLocal> {
    override fun toDomain(): PokemonListItemLocal = PokemonListItemLocal(
        pid = pid,
        koName = koName,
        dayTimeColor = dayTimeColor,
        nightTimeColor = nightTimeColor
    )
}