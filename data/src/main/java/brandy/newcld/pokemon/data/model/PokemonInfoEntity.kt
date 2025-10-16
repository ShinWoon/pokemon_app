package brandy.newcld.pokemon.data.model

data class PokemonInfoEntity (
    val id: Int,
    val name: String,
    val baseExperience: Int,
    val height: Int,
    val isDefault: Boolean,
    val order: Int,
    val weight: Int,
    val abilities: List<AbilitiesEntity>,
    val forms: List<NameUrlEntity>,
    val heldItems: List<HeldItemEntity>,
    val locationAreaEncounters: String,
    val moves: List<MovesEntity>,
    val species: NameUrlEntity,
    val sprites: SpritesEntity,
    val cries: CriesEntity,
    val stats: List<StatsEntity>,
    val types: List<TypesEntity>
)