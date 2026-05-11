package brandy.newcld.pokemon.presentation.model

data class SearchResultItem(
    val id: Int,
    val koName: String,
    val imageUrl: String,
)

fun PokemonItemLocalModel.toSearchResultItem(): SearchResultItem = SearchResultItem(
    id = id,
    koName = koName,
    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png",
)
