package com.example.moviesapp.di

import com.example.data.MoviesRepositoryImpl
import com.example.data.datasource.local.MoviesDao
import com.example.data.datasource.remote.MoviesApiService
import com.example.data.datasource.remote.MoviesPagingSource
import com.example.domain.repositories.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {
    @Provides
    @Singleton
    fun provideMoviesRepository(apiService: MoviesApiService,moviesDao: MoviesDao):MoviesRepository{
        return MoviesRepositoryImpl(apiService,moviesDao)
    }
}