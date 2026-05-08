package brandy.newcld.pokemon.data.repositoryImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import brandy.newcld.pokemon.data.bound.flowDataResource
import brandy.newcld.pokemon.data.local.PokemonLocalDataSource
import brandy.newcld.pokemon.data.remote.PokemonListPagingSource
import brandy.newcld.pokemon.data.remote.PokemonRemoteDataSource
import brandy.newcld.pokemon.data.toDomainModel
import brandy.newcld.pokemon.data.toDomainPaging
import brandy.newcld.pokemon.data.model.EvolutionChainEntity
import brandy.newcld.pokemon.dataresource.DataResource
import brandy.newcld.pokemon.domain.model.Ability
import brandy.newcld.pokemon.domain.model.EvolutionChain
import brandy.newcld.pokemon.domain.model.NameUrl
import brandy.newcld.pokemon.domain.model.PokemonDetailLocalInfoItem
import brandy.newcld.pokemon.domain.model.PokemonInfo
import brandy.newcld.pokemon.domain.model.PokemonListItemLocal
import brandy.newcld.pokemon.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val remoteDataSource: PokemonRemoteDataSource,
    private val localDataSource: PokemonLocalDataSource
): PokemonRepository {
    override fun getPokemonInfo(id: Int): Flow<DataResource<PokemonInfo>> = flowDataResource { remoteDataSource.getPokemonInfo(id) }

    override fun getPokemonList(): Flow<PagingData<NameUrl>> = Pager(
        config = PagingConfig (
            pageSize = PAGESIZE,
            initialLoadSize = 200,
            prefetchDistance = 100,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { PokemonListPagingSource(remoteDataSource) }
    ).flow.map { it.toDomainPaging() }

    override fun updateBackgroundColors(
        pid: Int,
        dayTimeColor: String,
        nightTimeColor: String
    ): Flow<DataResource<Unit>> = flowDataResource { localDataSource.updateBackgroundColors(pid, dayTimeColor, nightTimeColor) }

    override fun getAllLocal(): Flow<List<PokemonListItemLocal>> =
        localDataSource.getAllLocal().map { it.toDomainModel() }

    override fun getPokemonDetailLocalInfo(pid: Int): Flow<DataResource<PokemonDetailLocalInfoItem>> = flowDataResource {
        localDataSource.getPokemonDetailLocalInfo(pid = pid)
    }

    override fun getPokemonSpecies(id: Int): Flow<DataResource<String>> = flowDataResource {
        remoteDataSource.getPokemonSpecies(id)
    }

    override fun getAbility(name: String): Flow<DataResource<Ability>> = flowDataResource {
        localDataSource.getAbility(name) ?: remoteDataSource.getAbility(name).also {
            localDataSource.saveAbility(it)
        }
    }

    override fun getEvolutionChain(pokemonId: Int): Flow<DataResource<EvolutionChain>> = flowDataResource {
        val chainId = remoteDataSource.getEvolutionChainIdForPokemon(pokemonId)
            ?: throw IllegalStateException("Evolution chain id not found for pokemon $pokemonId")
        localDataSource.getEvolutionChain(chainId) ?: run {
            val remote = remoteDataSource.getEvolutionChain(chainId)
            val enriched = enrichWithKoNames(remote)
            localDataSource.saveEvolutionChain(enriched)
            enriched
        }
    }

    private suspend fun enrichWithKoNames(chain: EvolutionChainEntity): EvolutionChainEntity =
        chain.copy(
            stages = chain.stages.map { stage ->
                stage.copy(
                    koName = localDataSource.getKoNameByPokemonId(stage.speciesId) ?: stage.engName
                )
            }
        )

    companion object {
        val PAGESIZE = 300
    }
}