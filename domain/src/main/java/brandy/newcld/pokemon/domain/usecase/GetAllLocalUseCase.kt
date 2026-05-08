package brandy.newcld.pokemon.domain.usecase

import brandy.newcld.pokemon.domain.model.PokemonListItemLocal
import brandy.newcld.pokemon.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllLocalUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    operator fun invoke(): Flow<List<PokemonListItemLocal>> = pokemonRepository.getAllLocal()
}
