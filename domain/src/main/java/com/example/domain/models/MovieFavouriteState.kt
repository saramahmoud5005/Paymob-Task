package com.example.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_favourite_state")
data class MovieFavouriteState(
    @PrimaryKey
    val id:Long,
    val isFavourite:Boolean
)
