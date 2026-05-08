package brandy.newcld.pokemon.domain.usecase

import brandy.newcld.pokemon.dataresource.DataResource
import brandy.newcld.pokemon.domain.model.Ability
import brandy.newcld.pokemon.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAbilityUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    operator fun invoke(name: String): Flow<DataResource<Ability>> =
        pokemonRepository.getAbility(name)
}
