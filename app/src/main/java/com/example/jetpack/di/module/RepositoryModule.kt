package com.example.jetpack.di.module

import com.example.jetpack.AppDatabase
import com.example.jetpack.api.ApiService
import com.example.jetpack.repository.HomeRepository
import com.example.jetpack.repository.PopularMoviesRepository
import com.example.jetpack.repository.TopRatedRepository
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
    fun providesMoviesRepository(apiService: ApiService): PopularMoviesRepository = PopularMoviesRepository (apiService)

    @Provides
    @Singleton
    fun providesTvShowsRepository(apiService: ApiService): TopRatedRepository = TopRatedRepository (apiService)

    @Provides
    @Singleton
    fun providesHomeRepository(apiService: ApiService): HomeRepository = HomeRepository(apiService)

}