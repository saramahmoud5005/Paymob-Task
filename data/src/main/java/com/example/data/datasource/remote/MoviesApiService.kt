package com.example.data.datasource.remote

import com.example.domain.models.MovieDetailsResponse
import com.example.domain.models.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiService {
    @GET("/3/movie/popular")
    suspend fun getPopularMovies(@Query("page") pageNumber: Int):MoviesResponse

    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") id:Int): Response<MovieDetailsResponse>
}