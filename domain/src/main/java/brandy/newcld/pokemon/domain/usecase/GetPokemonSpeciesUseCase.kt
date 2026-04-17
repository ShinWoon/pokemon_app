package brandy.newcld.pokemon.domain.usecase

import brandy.newcld.pokemon.dataresource.DataResource
import brandy.newcld.pokemon.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonSpeciesUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    operator fun invoke(id: Int): Flow<DataResource<String>> = pokemonRepository.getPokemonSpecies(id)
}
