package com.example.data.datasource.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.MoviesRepositoryImpl
import com.example.domain.models.MoviesResponse
import com.example.domain.repositories.MoviesRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class
MoviesPagingSource @Inject constructor(
    private val moviesRepository: MoviesRepository
): PagingSource<Int, MoviesResponse.Movie>() {
    override fun getRefreshKey(state: PagingState<Int, MoviesResponse.Movie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesResponse.Movie> {
        val pageNum = params.key ?:1
        val movies = moviesRepository.getPopularMovies(pageNum)
        return try {
            LoadResult.Page(
                data = movies.results,
                prevKey = if (pageNum == 1) null else (pageNum-1),
                nextKey = if(pageNum.toLong() == movies.totalPages) null else (pageNum + 1)
            )
        }catch (e:IOException){
            LoadResult.Error(e)
        }catch (e:HttpException){
            LoadResult.Error(e)
        }
    }
}