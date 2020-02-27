package com.example.jetpack.di.module

import com.example.jetpack.AppDatabase
import com.example.jetpack.api.ApiService
import com.example.jetpack.repository.LoginRepository
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


}