package com.example.data

import com.example.data.datasource.local.MoviesDao
import com.example.data.datasource.remote.MoviesApiService
import com.example.domain.models.MovieDetailsResponse
import com.example.domain.models.MovieFavouriteState
import com.example.domain.repositories.MoviesRepository
import retrofit2.Response
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val apiService: MoviesApiService,
    private val moviesDao: MoviesDao
): MoviesRepository {
    override suspend fun getPopularMovies(page:Int) = apiService.getPopularMovies(page)
    override suspend fun getMovieDetails(id: Long): Response<MovieDetailsResponse> {
        return apiService.getMovieDetails(id)
    }
    override suspend fun insertMovieFavouriteState(id:Long){
        val movie = moviesDao.getMovieFavouriteState(id)
        if(movie!=null){
            moviesDao.insertMovieFavouriteState(
                MovieFavouriteState(
                    id = movie.id,
                    isFavourite = !(movie.isFavourite)
                )
            )
        }
        else{
            moviesDao.insertMovieFavouriteState(
                MovieFavouriteState(
                    id = movie.id,
                    isFavourite = true
                )
            )
        }

    }
}