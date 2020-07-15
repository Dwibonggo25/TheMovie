package com.example.jetpack.repository

import com.example.jetpack.API_KEY
import com.example.jetpack.api.ApiService
import javax.inject.Inject

class DetailMoviesRepository @Inject constructor(private val api: ApiService) {

    suspend fun fetchDetailMovies (movieId: String) = api.fetchDetailMovies(API_KEY, movieId)
}