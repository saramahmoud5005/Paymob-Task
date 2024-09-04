package com.example.domain.repositories

import androidx.paging.PagingData
import com.example.domain.models.MovieDetailsResponse
import com.example.domain.models.MoviesResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


interface MoviesRepository {
    suspend fun getPopularMovies(page:Int) : MoviesResponse
    suspend fun getMovieDetails(id: Long) : Response<MovieDetailsResponse>
}