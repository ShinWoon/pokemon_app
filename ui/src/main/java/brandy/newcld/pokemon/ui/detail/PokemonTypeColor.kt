package brandy.newcld.pokemon.ui.detail

import androidx.compose.ui.graphics.Color
import brandy.newcld.pokemon.ui.theme.DarkBugBg
import brandy.newcld.pokemon.ui.theme.DarkBugBorder
import brandy.newcld.pokemon.ui.theme.DarkBugText
import brandy.newcld.pokemon.ui.theme.DarkDarkBg
import brandy.newcld.pokemon.ui.theme.DarkDarkBorder
import brandy.newcld.pokemon.ui.theme.DarkDarkText
import brandy.newcld.pokemon.ui.theme.DarkDragonBg
import brandy.newcld.pokemon.ui.theme.DarkDragonBorder
import brandy.newcld.pokemon.ui.theme.DarkDragonText
import brandy.newcld.pokemon.ui.theme.DarkElectricBg
import brandy.newcld.pokemon.ui.theme.DarkElectricBorder
import brandy.newcld.pokemon.ui.theme.DarkElectricText
import brandy.newcld.pokemon.ui.theme.DarkFairyBg
import brandy.newcld.pokemon.ui.theme.DarkFairyBorder
import brandy.newcld.pokemon.ui.theme.DarkFairyText
import brandy.newcld.pokemon.ui.theme.DarkFightingBg
import brandy.newcld.pokemon.ui.theme.DarkFightingBorder
import brandy.newcld.pokemon.ui.theme.DarkFightingText
import brandy.newcld.pokemon.ui.theme.DarkFireBg
import brandy.newcld.pokemon.ui.theme.DarkFireBorder
import brandy.newcld.pokemon.ui.theme.DarkFireText
import brandy.newcld.pokemon.ui.theme.DarkFlyingBg
import brandy.newcld.pokemon.ui.theme.DarkFlyingBorder
import brandy.newcld.pokemon.ui.theme.DarkFlyingText
import brandy.newcld.pokemon.ui.theme.DarkGhostBg
import brandy.newcld.pokemon.ui.theme.DarkGhostBorder
import brandy.newcld.pokemon.ui.theme.DarkGhostText
import brandy.newcld.pokemon.ui.theme.DarkGrassBg
import brandy.newcld.pokemon.ui.theme.DarkGrassBorder
import brandy.newcld.pokemon.ui.theme.DarkGrassText
import brandy.newcld.pokemon.ui.theme.DarkGroundBg
import brandy.newcld.pokemon.ui.theme.DarkGroundBorder
import brandy.newcld.pokemon.ui.theme.DarkGroundText
import brandy.newcld.pokemon.ui.theme.DarkIceBg
import brandy.newcld.pokemon.ui.theme.DarkIceBorder
import brandy.newcld.pokemon.ui.theme.DarkIceText
import brandy.newcld.pokemon.ui.theme.DarkNormalBg
import brandy.newcld.pokemon.ui.theme.DarkNormalBorder
import brandy.newcld.pokemon.ui.theme.DarkNormalText
import brandy.newcld.pokemon.ui.theme.DarkPoisonBg
import brandy.newcld.pokemon.ui.theme.DarkPoisonBorder
import brandy.newcld.pokemon.ui.theme.DarkPoisonText
import brandy.newcld.pokemon.ui.theme.DarkPsychicBg
import brandy.newcld.pokemon.ui.theme.DarkPsychicBorder
import brandy.newcld.pokemon.ui.theme.DarkPsychicText
import brandy.newcld.pokemon.ui.theme.DarkRockBg
import brandy.newcld.pokemon.ui.theme.DarkRockBorder
import brandy.newcld.pokemon.ui.theme.DarkRockText
import brandy.newcld.pokemon.ui.theme.DarkSteelBg
import brandy.newcld.pokemon.ui.theme.DarkSteelBorder
import brandy.newcld.pokemon.ui.theme.DarkSteelText
import brandy.newcld.pokemon.ui.theme.DarkWaterBg
import brandy.newcld.pokemon.ui.theme.DarkWaterBorder
import brandy.newcld.pokemon.ui.theme.DarkWaterText
import brandy.newcld.pokemon.ui.theme.LightBugBg
import brandy.newcld.pokemon.ui.theme.LightBugBorder
import brandy.newcld.pokemon.ui.theme.LightBugText
import brandy.newcld.pokemon.ui.theme.LightDarkBg
import brandy.newcld.pokemon.ui.theme.LightDarkBorder
import brandy.newcld.pokemon.ui.theme.LightDarkText
import brandy.newcld.pokemon.ui.theme.LightDragonBg
import brandy.newcld.pokemon.ui.theme.LightDragonBorder
import brandy.newcld.pokemon.ui.theme.LightDragonText
import brandy.newcld.pokemon.ui.theme.LightElectricBg
import brandy.newcld.pokemon.ui.theme.LightElectricBorder
import brandy.newcld.pokemon.ui.theme.LightElectricText
import brandy.newcld.pokemon.ui.theme.LightFairyBg
import brandy.newcld.pokemon.ui.theme.LightFairyBorder
import brandy.newcld.pokemon.ui.theme.LightFairyText
import brandy.newcld.pokemon.ui.theme.LightFightingBg
import brandy.newcld.pokemon.ui.theme.LightFightingBorder
import brandy.newcld.pokemon.ui.theme.LightFightingText
import brandy.newcld.pokemon.ui.theme.LightFireBg
import brandy.newcld.pokemon.ui.theme.LightFireBorder
import brandy.newcld.pokemon.ui.theme.LightFireText
import brandy.newcld.pokemon.ui.theme.LightFlyingBg
import brandy.newcld.pokemon.ui.theme.LightFlyingBorder
import brandy.newcld.pokemon.ui.theme.LightFlyingText
import brandy.newcld.pokemon.ui.theme.LightGhostBg
import brandy.newcld.pokemon.ui.theme.LightGhostBorder
import brandy.newcld.pokemon.ui.theme.LightGhostText
import brandy.newcld.pokemon.ui.theme.LightGrassBg
import brandy.newcld.pokemon.ui.theme.LightGrassBorder
import brandy.newcld.pokemon.ui.theme.LightGrassText
import brandy.newcld.pokemon.ui.theme.LightGroundBg
import brandy.newcld.pokemon.ui.theme.LightGroundBorder
import brandy.newcld.pokemon.ui.theme.LightGroundText
import brandy.newcld.pokemon.ui.theme.LightIceBg
import brandy.newcld.pokemon.ui.theme.LightIceBorder
import brandy.newcld.pokemon.ui.theme.LightIceText
import brandy.newcld.pokemon.ui.theme.LightNormalBg
import brandy.newcld.pokemon.ui.theme.LightNormalBorder
import brandy.newcld.pokemon.ui.theme.LightNormalText
import brandy.newcld.pokemon.ui.theme.LightPoisonBg
import brandy.newcld.pokemon.ui.theme.LightPoisonBorder
import brandy.newcld.pokemon.ui.theme.LightPoisonText
import brandy.newcld.pokemon.ui.theme.LightPsychicBg
import brandy.newcld.pokemon.ui.theme.LightPsychicBorder
import brandy.newcld.pokemon.ui.theme.LightPsychicText
import brandy.newcld.pokemon.ui.theme.LightRockBg
import brandy.newcld.pokemon.ui.theme.LightRockBorder
import brandy.newcld.pokemon.ui.theme.LightRockText
import brandy.newcld.pokemon.ui.theme.LightSteelBg
import brandy.newcld.pokemon.ui.theme.LightSteelBorder
import brandy.newcld.pokemon.ui.theme.LightSteelText
import brandy.newcld.pokemon.ui.theme.LightWaterBg
import brandy.newcld.pokemon.ui.theme.LightWaterBorder
import brandy.newcld.pokemon.ui.theme.LightWaterText

enum class PokemonType {
    NORMAL, FIRE, WATER, ELECTRIC, GRASS, ICE,
    FIGHTING, POISON, GROUND, FLYING, PSYCHIC, BUG,
    ROCK, GHOST, DRAGON, DARK, STEEL, FAIRY
}

data class PokemonTypeColor(
    val background: Color,
    val border: Color,
    val textColor: Color,
)

object PokemonTypeColors {

    val light: Map<PokemonType, PokemonTypeColor> = mapOf(
        PokemonType.NORMAL    to PokemonTypeColor(LightNormalBg, LightNormalBorder, LightNormalText),
        PokemonType.FIRE      to PokemonTypeColor(LightFireBg, LightFireBorder, LightFireText),
        PokemonType.WATER     to PokemonTypeColor(LightWaterBg, LightWaterBorder, LightWaterText),
        PokemonType.ELECTRIC  to PokemonTypeColor(LightElectricBg, LightElectricBorder, LightElectricText),
        PokemonType.GRASS     to PokemonTypeColor(LightGrassBg, LightGrassBorder, LightGrassText),
        PokemonType.ICE       to PokemonTypeColor(LightIceBg, LightIceBorder, LightIceText),
        PokemonType.FIGHTING  to PokemonTypeColor(LightFightingBg, LightFightingBorder, LightFightingText),
        PokemonType.POISON    to PokemonTypeColor(LightPoisonBg, LightPoisonBorder, LightPoisonText),
        PokemonType.GROUND    to PokemonTypeColor(LightGroundBg, LightGroundBorder, LightGroundText),
        PokemonType.FLYING    to PokemonTypeColor(LightFlyingBg, LightFlyingBorder, LightFlyingText),
        PokemonType.PSYCHIC   to PokemonTypeColor(LightPsychicBg, LightPsychicBorder, LightPsychicText),
        PokemonType.BUG       to PokemonTypeColor(LightBugBg, LightBugBorder, LightBugText),
        PokemonType.ROCK      to PokemonTypeColor(LightRockBg, LightRockBorder, LightRockText),
        PokemonType.GHOST     to PokemonTypeColor(LightGhostBg, LightGhostBorder, LightGhostText),
        PokemonType.DRAGON    to PokemonTypeColor(LightDragonBg, LightDragonBorder, LightDragonText),
        PokemonType.DARK      to PokemonTypeColor(LightDarkBg, LightDarkBorder, LightDarkText),
        PokemonType.STEEL     to PokemonTypeColor(LightSteelBg, LightSteelBorder, LightSteelText),
        PokemonType.FAIRY     to PokemonTypeColor(LightFairyBg, LightFairyBorder, LightFairyText),
    )

    val dark: Map<PokemonType, PokemonTypeColor> = mapOf(
        PokemonType.NORMAL    to PokemonTypeColor(DarkNormalBg, DarkNormalBorder, DarkNormalText),
        PokemonType.FIRE      to PokemonTypeColor(DarkFireBg, DarkFireBorder, DarkFireText),
        PokemonType.WATER     to PokemonTypeColor(DarkWaterBg, DarkWaterBorder, DarkWaterText),
        PokemonType.ELECTRIC  to PokemonTypeColor(DarkElectricBg, DarkElectricBorder, DarkElectricText),
        PokemonType.GRASS     to PokemonTypeColor(DarkGrassBg, DarkGrassBorder, DarkGrassText),
        PokemonType.ICE       to PokemonTypeColor(DarkIceBg, DarkIceBorder, DarkIceText),
        PokemonType.FIGHTING  to PokemonTypeColor(DarkFightingBg, DarkFightingBorder, DarkFightingText),
        PokemonType.POISON    to PokemonTypeColor(DarkPoisonBg, DarkPoisonBorder, DarkPoisonText),
        PokemonType.GROUND    to PokemonTypeColor(DarkGroundBg, DarkGroundBorder, DarkGroundText),
        PokemonType.FLYING    to PokemonTypeColor(DarkFlyingBg, DarkFlyingBorder, DarkFlyingText),
        PokemonType.PSYCHIC   to PokemonTypeColor(DarkPsychicBg, DarkPsychicBorder, DarkPsychicText),
        PokemonType.BUG       to PokemonTypeColor(DarkBugBg, DarkBugBorder, DarkBugText),
        PokemonType.ROCK      to PokemonTypeColor(DarkRockBg, DarkRockBorder, DarkRockText),
        PokemonType.GHOST     to PokemonTypeColor(DarkGhostBg, DarkGhostBorder, DarkGhostText),
        PokemonType.DRAGON    to PokemonTypeColor(DarkDragonBg, DarkDragonBorder, DarkDragonText),
        PokemonType.DARK      to PokemonTypeColor(DarkDarkBg, DarkDarkBorder, DarkDarkText),
        PokemonType.STEEL     to PokemonTypeColor(DarkSteelBg, DarkSteelBorder, DarkSteelText),
        PokemonType.FAIRY     to PokemonTypeColor(DarkFairyBg, DarkFairyBorder, DarkFairyText),
    )

    fun getColor(type: PokemonType, isDarkMode: Boolean): PokemonTypeColor {
        val palette = if (isDarkMode) dark else light
        return palette[type] ?: light[PokemonType.NORMAL]!!
    }
}

fun pokemonTypeOf(name: String): PokemonType =
    runCatching { PokemonType.valueOf(name.uppercase()) }
        .getOrDefault(PokemonType.NORMAL)

