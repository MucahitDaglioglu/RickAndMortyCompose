package com.mucahitdaglioglu.rickandmorty.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mucahitdaglioglu.rickandmorty.data.repository.RickAndMortyRepository
import com.mucahitdaglioglu.rickandmorty.data.model.Result

class CharacterPagingSource(private val repository: RickAndMortyRepository) : PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val nextPage = params.key ?: 1
            val response = repository.getAllCharacters(nextPage)


            val data = response.body()?.results ?: emptyList()

            LoadResult.Page(
                data = data,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (data.isEmpty()) null else nextPage + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


}