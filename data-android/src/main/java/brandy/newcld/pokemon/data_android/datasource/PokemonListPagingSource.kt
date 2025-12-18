package brandy.newcld.pokemon.data_android.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import brandy.newcld.pokemon.data_android.model.PokemonNameUrlEntity
import java.io.IOException
import javax.inject.Inject

class PokemonListPagingSource @Inject constructor(
    private val remoteDataSource: PokemonListRemoteDataSource
): PagingSource<Int, PokemonNameUrlEntity>() {
    override fun getRefreshKey(state: PagingState<Int, PokemonNameUrlEntity>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonNameUrlEntity> {
        return try {
            val offset = params.key ?: 0
            val limit = params.loadSize

            val response = remoteDataSource.getPokemonList(offset = offset, limit = limit)

            val nextKey = if(response.isEmpty()) null else offset + response.size
            val prevKey = if(offset == 0) null else maxOf(offset - limit, 0)

            LoadResult.Page(
                data = response,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        }
    }
}