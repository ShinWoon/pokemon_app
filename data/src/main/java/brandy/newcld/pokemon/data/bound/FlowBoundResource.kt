package brandy.newcld.pokemon.data.bound

import brandy.newcld.pokemon.dataresource.DataResource
import kotlinx.coroutines.flow.FlowCollector

class FlowBoundResource<DomainType, DataType>(dataAction: suspend () -> DataType) : FlowBaseBoundResource<DomainType, DataType>(dataAction) {

    override suspend fun collect(collector: FlowCollector<DataResource<DomainType>>) {
        collector.emit(DataResource.loading<DomainType>() as DataResource<DomainType>)
        fetchFromSource(collector)
    }

}