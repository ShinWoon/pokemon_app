package brandy.newcld.pokemon.presentation.model

import brandy.newcld.pokemon.domain.model.PokemonInfo

data class PokemonInfoModel(
    val height: Int = 0,
    val weight: Int = 0,
    val type: String = "",
    val baseExperience: Int = 0,
    val stats: List<StatModel> = emptyList(),
    val abilities: List<AbilityModel> = emptyList(),
    val moves: List<String> = emptyList(),
    val imgUrl: String = "",
    val appBarIconUrl: String = "",
    val typeImgUrl: String = "",
    val cryLatestUrl: String = "",
    val cryLegacyUrl: String = "",
)

data class StatModel(
    val name: String,
    val value: Int,
)

data class AbilityModel(
    val name: String,
    val koName: String? = null,
    val isHidden: Boolean,
)

fun PokemonInfo.toPresentationModel(): PokemonInfoModel = PokemonInfoModel(
    height = height,
    weight = weight,
    type = types.firstOrNull()?.type?.name.orEmpty(),
    baseExperience = baseExperience,
    stats = stats.map { StatModel(it.stat.name, it.baseStat) },
    abilities = abilities.map { AbilityModel(name = it.ability.name, isHidden = it.isHidden) },
    moves = moves.map { it.move.name },
    imgUrl = sprites.other.officialArtwork.frontDefault,
    appBarIconUrl = sprites.frontDefault,
    typeImgUrl = types.firstOrNull()?.imgUrl ?: "",
    cryLatestUrl = cries.latest,
    cryLegacyUrl = cries.legacy,
)