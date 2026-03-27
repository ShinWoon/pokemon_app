package brandy.newcld.pokemon.domain.usecase

import brandy.newcld.pokemon.dataresource.DataResource
import brandy.newcld.pokemon.domain.model.PokemonDetailLocalInfoItem
import brandy.newcld.pokemon.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonDetailLocalInfoUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    operator fun invoke(pid: Int): Flow<DataResource<PokemonDetailLocalInfoItem>> = pokemonRepository.getPokemonDetailLocalInfo(pid = pid)
}