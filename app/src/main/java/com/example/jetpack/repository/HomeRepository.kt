package com.example.jetpack.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.jetpack.API_KEY
import com.example.jetpack.LOGIN_CODE
import com.example.jetpack.MOVIE_CODE
import com.example.jetpack.api.ApiService
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api: ApiService, private var preferences: SharedPreferences) {

    suspend fun fetchNowPlaying() = api.fetchNowPlayingMovies(API_KEY)

    suspend fun fetchUpcomingMovies() = api.fetchUpcomingMovies(API_KEY)

    fun saveSharedPreferences(context: Context) {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        preferences = EncryptedSharedPreferences.create(
            context,
            MOVIE_CODE,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        preferences.edit().apply(){
            putString(LOGIN_CODE, "code save")
        }.apply()
    }
}