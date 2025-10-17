package brandy.newcld.pokemon.domain.repository

import brandy.newcld.pokemon.dataresource.DataResource
import brandy.newcld.pokemon.domain.model.NameUrl
import brandy.newcld.pokemon.domain.model.PokemonInfo
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonList(limit: Int, offset: Int): Flow<DataResource<List<NameUrl>>>

    fun getPokemonInfo(id: Int): Flow<DataResource<PokemonInfo>>
}