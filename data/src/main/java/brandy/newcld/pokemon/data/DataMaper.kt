package brandy.newcld.pokemon.data

internal interface DataMapper<DomainModel> {
    fun toDomain(): DomainModel
}

internal fun <EntityModel : DataMapper<DomainModel>, DomainModel> List<EntityModel>.toDomain(): List<DomainModel> {
    return map(DataMapper<DomainModel>::toDomain)
}

internal fun <EntityModel, DomainModel> EntityModel.toDomainModel(): DomainModel {
    @Suppress("UNCHECKED_CAST")
    return when (this) {
        is DataMapper<*> -> toDomain()
        is List<*> -> map {
            val domainModel: DomainModel = it.toDomainModel()
            domainModel
        }
        is Unit, Boolean, Int, String, Byte, Short, Long, Char -> this
        else -> {
            throw IllegalArgumentException("DataModel은 DataMapper<>, List<DataMapper<>>, Unit으로 구성되어야 합니다")
        }
    } as DomainModel
}