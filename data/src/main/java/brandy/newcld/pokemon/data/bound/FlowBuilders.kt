package brandy.newcld.pokemon.data.bound

fun <DataType, DomainType> flowDataResource(dataAction: suspend() -> DataType) =
    FlowBoundResource<DomainType, DataType>(dataAction)