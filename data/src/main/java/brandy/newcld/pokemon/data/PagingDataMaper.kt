package brandy.newcld.pokemon.data

import androidx.paging.PagingData
import androidx.paging.map

internal interface DomainMappable<D> {
    fun toDomain(): D
}

internal fun <E : DomainMappable<D>, D : Any> PagingData<E>.toDomainPaging(): PagingData<D> =
    map { it.toDomain() }

internal fun <E : DomainMappable<D>, D> List<E>.toDomainList(): List<D> =
    map { it.toDomain() }