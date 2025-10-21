package brandy.newcld.pokemon.domain.model

data class PokemonInfo (
    val id: Int,
    val name: String,
    val baseExperience: Int,
    val height: Int,
    val isDefault: Boolean,
    val order: Int,
    val weight: Int,
    val abilities: List<Abilities>,
    val forms: List<NameUrl>,
    val heldItems: List<HeldItem>,
    val locationAreaEncounters: String,
    val moves: List<Moves>,
    val species: NameUrl,
    val sprites: Sprites,
    val cries: Cries,
    val stats: List<Stats>,
    val types: List<Types>
)