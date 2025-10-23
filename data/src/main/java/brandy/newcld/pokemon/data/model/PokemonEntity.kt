package brandy.newcld.pokemon.data.model

import brandy.newcld.pokemon.data.DataMapper

data class PokemonEntity (
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<NameUrlEntity>
)