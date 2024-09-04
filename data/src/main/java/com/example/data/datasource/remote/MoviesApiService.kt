package com.example.data.datasource.remote

import com.example.domain.models.MovieDetailsResponse
import com.example.domain.models.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val API_KEY = "f04a53f4cfd860653f77572d844d6977"
interface MoviesApiService {
    @GET("/3/movie/popular")
    suspend fun getPopularMovies(
        @Query("page") pageNumber: Int,
        @Query("api_key") apiKey: String = API_KEY,
    ): MoviesResponse

    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") id: Long,
        @Query("api_key") apiKey: String = API_KEY,
    ): Response<MovieDetailsResponse>
}