package brandy.newcld.pokemon.data.model

data class PokemonEntity (
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<NameUrlEntity>
)