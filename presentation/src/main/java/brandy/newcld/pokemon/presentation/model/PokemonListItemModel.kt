package brandy.newcld.pokemon.presentation.model

import brandy.newcld.pokemon.domain.model.NameUrl

data class PokemonListItemModel (
    val id: Int = 1,
    val name: String = "",
    val imageUrl: String = ""
)

fun NameUrl.toPokemonListItemModel(): PokemonListItemModel {
    val id = getId(url)
    return PokemonListItemModel(
        id = id,
        name = name,
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png"
    )
}

fun getId(url: String): Int {
    return url.trimEnd('/').split("/").last().toInt()
}