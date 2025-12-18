package brandy.newcld.pokemon.domain.repository

import androidx.paging.PagingData
import brandy.newcld.pokemon.dataresource.DataResource
import brandy.newcld.pokemon.domain.model.NameUrl
import brandy.newcld.pokemon.domain.model.PokemonInfo
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonInfo(id: Int): Flow<DataResource<PokemonInfo>>

    fun getPokemonList(limit: Int, offset: Int): Flow<PagingData<List<NameUrl>>>
}