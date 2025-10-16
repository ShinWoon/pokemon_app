package brandy.newcld.pokemon.data.model

data class HeldItemEntity(
    val item: NameUrlEntity,
    val versionDetails: List<VersionDetailEntity>
) {
    data class VersionDetailEntity(val rarity: Int, val version: NameUrlEntity)
}
