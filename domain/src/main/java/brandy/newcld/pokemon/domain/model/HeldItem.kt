package brandy.newcld.pokemon.domain.model

data class HeldItem(
    val item: NameUrl,
    val versionDetails: List<VersionDetail>
) {

}

data class VersionDetail(val rarity: Int, val version: NameUrl)