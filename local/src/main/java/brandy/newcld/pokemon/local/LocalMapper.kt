package brandy.newcld.pokemon.local

internal interface LocalMapper<DataModel> {
    fun toData(): DataModel
}

internal fun <EntityModel : LocalMapper<DataModel>, DataModel> List<EntityModel>.toData(): List<DataModel> {
    return map(LocalMapper<DataModel>::toData)
}