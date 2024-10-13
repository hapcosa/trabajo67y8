package com.example.trabajo67y8.utils

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.trabajo67y8.data.RestDataSource
import com.example.trabajo67y8.models.Movie
import com.example.trabajo67y8.models.MovieDto

class MyPagingSource(private val apiService: RestDataSource) : PagingSource<Int, MovieDto>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDto> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = apiService.getMoviesList(category="popular", page=nextPageNumber)
            LoadResult.Page(
                    data = response.results,
            prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
            nextKey = if (response.results.isEmpty()) null else nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, MovieDto>): Int? {
        return null
    }
}