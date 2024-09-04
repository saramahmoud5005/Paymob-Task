package com.example.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.domain.models.MovieFavouriteState

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieFavouriteState(movieFavouriteState: MovieFavouriteState)

    @Query("SELECT * FROM movie_favourite_state WHERE id = :id")
    suspend fun getMovieFavouriteState(id: Long): MovieFavouriteState

//    @Update(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun updateMovieFavouriteState(movieFavouriteState: MovieFavouriteState)
}