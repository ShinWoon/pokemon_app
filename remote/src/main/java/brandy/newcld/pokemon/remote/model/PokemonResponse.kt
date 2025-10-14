package brandy.newcld.pokemon.remote.model


import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("base_experience")
    val baseExperience: Int,
    @SerializedName("height")
    val height: Int,
    @SerializedName("is_default")
    val isDefault: Boolean,
    @SerializedName("order")
    val order: Int,
    @SerializedName("weight")
    val weight: Int,
    @SerializedName("abilities")
    val abilities: List<AbilitiesResponse>,
    @SerializedName("forms")
    val forms: List<NameUrlResponse>,
    @SerializedName("held_items")
    val heldItems: List<HeldItemResponse>,
    @SerializedName("location_area_encounters")
    val locationAreaEncounters: String,
    @SerializedName("moves")
    val moves: List<MovesResponse>,
    @SerializedName("species")
    val species: NameUrlResponse,
    @SerializedName("sprites")
    val sprites: SpritesResponse,
    @SerializedName("cries")
    val cries: CriesResponse,
    @SerializedName("stats")
    val stats: List<StatsResponse>,
    @SerializedName("types")
    val types: List<TypesResponse>
)