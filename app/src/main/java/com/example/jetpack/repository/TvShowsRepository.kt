package com.example.jetpack.repository

import com.example.jetpack.API_KEY
import com.example.jetpack.api.ApiService
import javax.inject.Inject

class TvShowsRepository @Inject constructor(private val api: ApiService) {

    fun fecthTvMoviesData() = api.fetchTvShowsFilm(API_KEY)
}