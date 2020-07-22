package com.example.jetpack.repository

import com.example.jetpack.API_KEY
import com.example.jetpack.api.ApiService
import com.example.jetpack.db.Favorite
import com.example.jetpack.db.FavoriteDao
import javax.inject.Inject

class DetailMoviesRepository @Inject constructor(private val api: ApiService, private val dao: FavoriteDao) {

    suspend fun fetchDetailMovies (movieId: String) = api.fetchDetailMovies( movieId, API_KEY)

    suspend fun saveDataFavorite(data: Favorite) {
        dao.insertUserData(data)
    }

    suspend fun fetchMoviesInFavorite (id: Long) = dao.fetchDataInFavorite(id)
}