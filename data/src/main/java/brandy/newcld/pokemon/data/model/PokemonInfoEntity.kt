package brandy.newcld.pokemon.data.model

import brandy.newcld.pokemon.data.DataMapper
import brandy.newcld.pokemon.data.toDomain
import brandy.newcld.pokemon.domain.model.PokemonInfo

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
): DataMapper<PokemonInfo> {
    override fun toDomain(): PokemonInfo = PokemonInfo(
        id,
        name,
        baseExperience,
        height,
        isDefault,
        order,
        weight,
        abilities.toDomain(),
        forms.toDomain(),
        heldItems.toDomain(),
        locationAreaEncounters,
        moves.toDomain(),
        species.toDomain(),
        sprites.toDomain(),
        cries.toDomain(),
        stats.toDomain(),
        types.toDomain()
    )
}