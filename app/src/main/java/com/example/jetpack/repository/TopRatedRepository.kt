package com.example.jetpack.repository

import com.example.jetpack.API_KEY
import com.example.jetpack.api.ApiService
import javax.inject.Inject

class TopRatedRepository @Inject constructor(private val api: ApiService) {

    fun fecthTopRatedMovies() = api.fetchTopRatesMovies(API_KEY)

}