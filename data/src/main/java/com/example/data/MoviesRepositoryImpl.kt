package com.example.data

import com.example.data.datasource.remote.MoviesApiService
import com.example.domain.models.MovieDetailsResponse
import com.example.domain.repositories.MoviesRepository
import retrofit2.Response
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val apiService: MoviesApiService
): MoviesRepository {
    override suspend fun getPopularMovies(page:Int) = apiService.getPopularMovies(page)
    override suspend fun getMovieDetails(id: Long): Response<MovieDetailsResponse> {
        return apiService.getMovieDetails(id)
    }
}