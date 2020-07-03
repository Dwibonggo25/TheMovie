package com.example.jetpack.repository

import com.example.jetpack.API_KEY
import com.example.jetpack.api.ApiService
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api: ApiService) {

    suspend fun fetchNowPlaying() = api.fetchNowPlayingMovies(API_KEY)

    suspend fun fetchUpcomingMovies() = api.fetchUpcomingMovies(API_KEY)
}