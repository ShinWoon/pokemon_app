package com.example.pokemonwidget.dao


import com.google.gson.annotations.SerializedName

data class PokemonSpecies(
    @SerializedName("color")
    val color: Color,
    @SerializedName("egg_groups")
    val eggGroups: List<EggGroup>,
    @SerializedName("flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntry>,
    @SerializedName("form_descriptions")
    val formDescriptions: List<Any>,
    @SerializedName("forms_switchable")
    val formsSwitchable: Boolean,
    @SerializedName("gender_rate")
    val genderRate: Int,
    @SerializedName("genera")
    val genera: List<Genera>,
    @SerializedName("generation")
    val generation: Generation,
    @SerializedName("growth_rate")
    val growthRate: GrowthRate,
    @SerializedName("habitat")
    val habitat: Habitat,
    @SerializedName("is_baby")
    val isBaby: Boolean,
    @SerializedName("is_legendary")
    val isLegendary: Boolean,
    @SerializedName("is_mythical")
    val isMythical: Boolean,
    @SerializedName("names")
    val names: List<Name>
)