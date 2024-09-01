package com.example.domain.repositories

import com.example.domain.models.Movie

interface MoviesRepository {
    suspend fun getMovies():List<Movie>
}