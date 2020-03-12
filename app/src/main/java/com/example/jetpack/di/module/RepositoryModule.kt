package com.example.jetpack.di.module

import com.example.jetpack.AppDatabase
import com.example.jetpack.api.ApiService
import com.example.jetpack.repository.LoginRepository
import com.example.jetpack.repository.MoviesRepository
import com.example.jetpack.repository.TvShowsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesUserDao (db: AppDatabase) = db.userDao()

    @Provides
    @Singleton
    fun providesLoginRepository (apiService: ApiService): LoginRepository = LoginRepository (apiService)

    @Provides
    @Singleton
    fun providesMoviesRepository(apiService: ApiService): MoviesRepository = MoviesRepository (apiService)

    @Provides
    @Singleton
    fun providesTvShowsRepository(apiService: ApiService): TvShowsRepository = TvShowsRepository (apiService)
}