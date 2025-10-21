package brandy.newcld.pokemon.domain.usecase

import brandy.newcld.pokemon.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonInfoUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    operator fun invoke(id: Int) = pokemonRepository.getPokemonInfo(id)
}