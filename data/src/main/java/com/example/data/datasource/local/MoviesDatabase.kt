package com.example.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.domain.models.MovieFavouriteState

@Database(entities = [MovieFavouriteState::class], version = 1, exportSchema = false)
abstract class MoviesDatabase:RoomDatabase() {
    abstract val moviesDao:MoviesDao
}