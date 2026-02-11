package brandy.newcld.pokemon.domain.usecase

import brandy.newcld.pokemon.dataresource.DataResource
import brandy.newcld.pokemon.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateBackgroundColorsUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
){
    operator fun invoke(pid: Int, dayTimeColor: String, nightTimeColor: String): Flow<DataResource<Unit>> = pokemonRepository.updateBackgroundColors(pid, dayTimeColor, nightTimeColor)
}