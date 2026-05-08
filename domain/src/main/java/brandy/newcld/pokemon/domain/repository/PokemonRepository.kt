package brandy.newcld.pokemon.domain.repository

import androidx.paging.PagingData
import brandy.newcld.pokemon.dataresource.DataResource
import brandy.newcld.pokemon.domain.model.Ability
import brandy.newcld.pokemon.domain.model.EvolutionChain
import brandy.newcld.pokemon.domain.model.NameUrl
import brandy.newcld.pokemon.domain.model.PokemonDetailLocalInfoItem
import brandy.newcld.pokemon.domain.model.PokemonInfo
import brandy.newcld.pokemon.domain.model.PokemonListItemLocal
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonInfo(id: Int): Flow<DataResource<PokemonInfo>>

    fun getPokemonList(): Flow<PagingData<NameUrl>>

    fun updateBackgroundColors(pid: Int, dayTimeColor: String, nightTimeColor: String): Flow<DataResource<Unit>>

    fun getAllLocal(): Flow<List<PokemonListItemLocal>>

    fun getPokemonDetailLocalInfo(pid: Int): Flow<DataResource<PokemonDetailLocalInfoItem>>

    fun getPokemonSpecies(id: Int): Flow<DataResource<String>>

    fun getAbility(name: String): Flow<DataResource<Ability>>

    fun getEvolutionChain(pokemonId: Int): Flow<DataResource<EvolutionChain>>
}