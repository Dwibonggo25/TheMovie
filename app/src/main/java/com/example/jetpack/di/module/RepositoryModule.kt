package com.example.jetpack.di.module

import android.content.SharedPreferences
import com.example.jetpack.AppDatabase
import com.example.jetpack.api.ApiService
import com.example.jetpack.db.FavoriteDao
import com.example.jetpack.repository.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesUserDao (db: AppDatabase) = db.favoriteDao()

    @Provides
    @Singleton
    fun providesMoviesRepository(apiService: ApiService): PopularMoviesRepository = PopularMoviesRepository (apiService)

    @Provides
    @Singleton
    fun providesTvShowsRepository(apiService: ApiService): TopRatedRepository = TopRatedRepository (apiService)

    @Provides
    @Singleton
    fun providesHomeRepository(apiService: ApiService, preferences: SharedPreferences): HomeRepository = HomeRepository(apiService, preferences)

    @Provides
    @Singleton
    fun providesDetailMoviesRepository(apiService: ApiService,favorite: FavoriteDao): DetailMoviesRepository = DetailMoviesRepository(apiService,favorite)

    @Provides
    @Singleton
    fun providesFavoriteRepository(apiService: ApiService,favorite: FavoriteDao): FavoriteRepository = FavoriteRepository(apiService,favorite)

}