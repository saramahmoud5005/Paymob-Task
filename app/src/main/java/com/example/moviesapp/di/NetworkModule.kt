package com.example.moviesapp.di

import android.content.Context
import androidx.room.Room
import com.example.data.datasource.local.MoviesDao
import com.example.data.datasource.local.MoviesDatabase
import com.example.data.datasource.remote.MoviesApiService
import com.example.data.datasource.remote.MoviesPagingSource
import com.example.domain.repositories.MoviesRepository
import com.example.moviesapp.TOKEN
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideInterceptor(): AuthInterceptor {
        return AuthInterceptor()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://api.themoviedb.org")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): MoviesApiService {
        return retrofit.create(MoviesApiService::class.java)
    }

    @Provides
    fun providePagingSource(moviesRepository: MoviesRepository): MoviesPagingSource {
        return MoviesPagingSource(moviesRepository)
    }

    @Provides
    @Singleton
    fun provideMoviesDatabase(@ApplicationContext context: Context): MoviesDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MoviesDatabase::class.java,
            name = "movies_db",
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideMoviesDao(moviesDatabase: MoviesDatabase): MoviesDao = moviesDatabase.moviesDao

}