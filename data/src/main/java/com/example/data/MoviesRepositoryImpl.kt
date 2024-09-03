package com.example.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.datasource.remote.MoviesApiService
import com.example.data.datasource.remote.MoviesPagingSource
import com.example.domain.models.MovieDetailsResponse
import com.example.domain.repositories.MoviesRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val apiService: MoviesApiService
): MoviesRepository {
    override suspend fun getPopularMovies(page:Int) = apiService.getPopularMovies(page)
    override suspend fun getMovieDetails(id: Int): Response<MovieDetailsResponse> {
        return apiService.getMovieDetails(id)
    }
}