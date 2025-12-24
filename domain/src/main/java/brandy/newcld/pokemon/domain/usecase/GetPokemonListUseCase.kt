package brandy.newcld.pokemon.domain.usecase

import androidx.paging.PagingData
import brandy.newcld.pokemon.dataresource.DataResource
import brandy.newcld.pokemon.domain.model.NameUrl
import brandy.newcld.pokemon.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    operator fun invoke(limit: Int, offset: Int): Flow<PagingData<NameUrl>> = pokemonRepository.getPokemonList(limit = limit,offset = offset)
}