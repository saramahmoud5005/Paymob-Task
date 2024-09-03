package com.example.data.datasource.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.MoviesRepositoryImpl
import com.example.domain.models.MoviesResponse
import com.example.domain.repositories.MoviesRepository
import javax.inject.Inject

class MoviesPagingSource @Inject constructor(
    private val moviesRepository: MoviesRepository
): PagingSource<Int, MoviesResponse.Movie>() {
    override fun getRefreshKey(state: PagingState<Int, MoviesResponse.Movie>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesResponse.Movie> {
        val pageNum = params.key ?:0
        val movies = moviesRepository.getPopularMovies(pageNum)

        return LoadResult.Page(
            data = movies.results,
            prevKey = if (pageNum == 0) null else pageNum,
            nextKey = if(pageNum.toLong() == movies.totalPages) null else (pageNum + 1)
        )
    }

}