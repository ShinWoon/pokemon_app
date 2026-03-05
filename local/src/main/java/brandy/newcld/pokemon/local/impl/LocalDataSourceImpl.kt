package brandy.newcld.pokemon.local.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import brandy.newcld.pokemon.data.local.PokemonLocalDataSource
import brandy.newcld.pokemon.data.model.PokemonDetailLocalInfoEntity
import brandy.newcld.pokemon.data.model.PokemonListItemLocalEntity
import brandy.newcld.pokemon.local.room.PokemonDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val pokemonDao: PokemonDao,
) : PokemonLocalDataSource {
    override suspend fun getPokemonDetailLocalInfo(pid: Int): PokemonDetailLocalInfoEntity =
        pokemonDao.getAll(pid = pid).toData()

    override fun getLocalPaging(): Flow<PagingData<PokemonListItemLocalEntity>> = Pager(
        config = PagingConfig(pageSize = 300, enablePlaceholders = false),
        pagingSourceFactory = { pokemonDao.getLocalPaging() }
    ).flow
        .map { it.map { entity -> entity.toData() } }

    override suspend fun updateBackgroundColors(
        pid: Int,
        dayTimeColor: String,
        nightTimeColor: String
    ) {
        pokemonDao.updateColor(pid = pid, dayTimeColor = dayTimeColor, nightTimeColor = nightTimeColor)
    }
}