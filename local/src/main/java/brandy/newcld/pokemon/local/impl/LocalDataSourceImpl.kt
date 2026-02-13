package brandy.newcld.pokemon.local.impl

import androidx.paging.PagingSource
import brandy.newcld.pokemon.data.local.PokemonLocalDataSource
import brandy.newcld.pokemon.data.model.PokemonAppBarEntity
import brandy.newcld.pokemon.data.model.PokemonListItemLocalEntity
import brandy.newcld.pokemon.local.room.PokemonDao
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val pokemonDao: PokemonDao,
) : PokemonLocalDataSource {
    override suspend fun getPokemonAppBarInfo(pid: Int): PokemonAppBarEntity =
        pokemonDao.getAll(pid = pid).toData()

    override fun getLocalPaging(): PagingSource<Int, PokemonListItemLocalEntity> = pokemonDao.getLocalPaging()

    override suspend fun updateBackgroundColors(
        pid: Int,
        dayTimeColor: String,
        nightTimeColor: String
    ) {
        pokemonDao.updateColor(pid = pid, dayTimeColor = dayTimeColor, nightTimeColor = nightTimeColor)
    }
}