package brandy.newcld.pokemon.local.impl

import brandy.newcld.pokemon.data.local.PokemonLocalDataSource
import brandy.newcld.pokemon.data.model.PokemonAppBarEntity
import brandy.newcld.pokemon.data.model.PokemonKoreanNameEntity
import brandy.newcld.pokemon.local.room.PokemonDao
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val pokemonDao: PokemonDao,
) : PokemonLocalDataSource {
    override suspend fun getPokemonAppBarInfo(pid: Int): PokemonAppBarEntity =
        pokemonDao.getAll(pid = pid).toData()

    override suspend fun getPokemonKoreanName(pid: Int): PokemonKoreanNameEntity =
        pokemonDao.getPokemonKoreanNameList().toData()
}