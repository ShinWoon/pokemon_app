package brandy.newcld.pokemon.domain.usecase

import androidx.paging.PagingData
import brandy.newcld.pokemon.domain.model.PokemonListItemLocal
import brandy.newcld.pokemon.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonLocalPagingUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    operator fun invoke(): Flow<PagingData<PokemonListItemLocal>> = pokemonRepository.getPokemonLocalPaging()
}