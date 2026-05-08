package brandy.newcld.pokemon.domain.usecase

import brandy.newcld.pokemon.dataresource.DataResource
import brandy.newcld.pokemon.domain.model.EvolutionChain
import brandy.newcld.pokemon.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEvolutionChainUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    operator fun invoke(pokemonId: Int): Flow<DataResource<EvolutionChain>> =
        pokemonRepository.getEvolutionChain(pokemonId)
}
