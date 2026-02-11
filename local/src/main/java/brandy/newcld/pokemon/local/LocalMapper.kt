package brandy.newcld.pokemon.local

internal interface LocalMapper<DataModel> {
    fun toData(): DataModel
}

internal fun <EntityModel : LocalMapper<DataModel>, DataModel> List<EntityModel>.toData(): List<DataModel> {
    return map(LocalMapper<DataModel>::toData)
}

internal fun <EntityModel, DataModel> EntityModel.toData(): DataModel {
    @Suppress("UNCHECKED_CAST")
    return when(this) {
        is LocalMapper<*> -> toData()
        is List<*> -> map {
            val dataModel: DataModel = it.toData()
            dataModel
        }
        is Unit, Boolean, Int, String, Byte, Short, Long, Char -> this
        else -> {
            throw IllegalArgumentException("LocalModel은 LocalMapper<>, List<LocalMapper<>>, Unit으로 구성되어야 합니다")
        }
    } as DataModel
}