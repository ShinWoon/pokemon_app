package brandy.newcld.pokemon.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import brandy.newcld.pokemon.data.model.NameUrlEntity
import java.io.IOException
import javax.inject.Inject

class PokemonListPagingSource @Inject constructor(
    private val remoteDataSource: PokemonRemoteDataSource
): PagingSource<Int, NameUrlEntity>() {
    override fun getRefreshKey(state: PagingState<Int, NameUrlEntity>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NameUrlEntity> {
        return try {
            val offset = params.key ?: 0
            val limit = PAGE_SIZE

            val response = remoteDataSource.getPokemonList(offset = offset, limit = limit)

            val nextKey = if (response.size < limit) null else offset + limit
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

    private companion object { const val PAGE_SIZE = 20 }
}