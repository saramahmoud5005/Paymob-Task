package com.example.moviesapp.di

import com.example.data.MoviesRepositoryImpl
//import com.example.data.usecases.GetMoviesUseCase
import com.example.domain.repositories.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {
//    @Provides
//    @Singleton
//    fun provideGetMoviesUseCase(moviesRepository: MoviesRepository):GetMoviesUseCase{
//        return GetMoviesUseCase(moviesRepository)
//    }
}