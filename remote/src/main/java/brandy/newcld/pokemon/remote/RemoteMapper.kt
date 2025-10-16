package brandy.newcld.pokemon.remote

interface RemoteMapper<DataModel> {
    fun toData(): DataModel
}

internal fun <RemoteModel : RemoteMapper<DataModel>, DataModel> List<RemoteModel>.toData(): List<DataModel> {
    return map(RemoteMapper<DataModel>::toData)
}