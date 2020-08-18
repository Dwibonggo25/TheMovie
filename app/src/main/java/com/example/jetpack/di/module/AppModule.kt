package com.example.jetpack.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.jetpack.SimpleApp
import com.example.jetpack.AppDatabase
import com.example.jetpack.PREF_NAME
import com.example.jetpack.api.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(subcomponents =  [MainActivityComponent::class])
class AppModule{

    @Provides
    @Singleton
    fun provideContext(app: SimpleApp) : Context = app

    @Provides
    @Singleton
    fun providesApplication(app: SimpleApp) : Application = app

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun providesDatabase (context: Context) = Room.databaseBuilder(context, AppDatabase::class.java, "simpleapp.db").fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun providesPreference(app : SimpleApp) : SharedPreferences  = app.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun providesSharedPreference(sharedPreferences: SharedPreferences) : SharedPreferences.Editor = sharedPreferences.edit()

}