package brandy.newcld.pokemon.data.model

import brandy.newcld.pokemon.data.DataMapper
import brandy.newcld.pokemon.data.toDomain
import brandy.newcld.pokemon.domain.model.HeldItem
import brandy.newcld.pokemon.domain.model.VersionDetail

data class HeldItemEntity(
    val item: NameUrlEntity,
    val versionDetails: List<VersionDetailEntity>
): DataMapper<HeldItem> {
    override fun toDomain(): HeldItem = HeldItem(item.toDomain(), versionDetails.toDomain())
}

data class VersionDetailEntity(val rarity: Int, val version: NameUrlEntity): DataMapper<VersionDetail> {
    override fun toDomain(): VersionDetail = VersionDetail(rarity, version.toDomain())
}
