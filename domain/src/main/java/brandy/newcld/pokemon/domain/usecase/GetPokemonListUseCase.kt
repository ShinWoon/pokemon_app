package brandy.newcld.pokemon.domain.usecase

import brandy.newcld.pokemon.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository

) {
    operator fun invoke(limit: Int, offset: Int) = pokemonRepository.getPokemonList(limit, offset)
}