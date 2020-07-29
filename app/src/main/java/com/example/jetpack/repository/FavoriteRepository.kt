package com.example.jetpack.repository

import com.example.jetpack.api.ApiService
import com.example.jetpack.db.Favorite
import com.example.jetpack.db.FavoriteDao
import javax.inject.Inject

class FavoriteRepository @Inject constructor(private val api: ApiService, private val dao: FavoriteDao) {

    suspend fun fetchAllDataInFavorite() = dao.fetchAllDataInFavorite()

    suspend fun deleteDataInFavorite(id: Int): List<Favorite> {
        dao.deleteDataInFavorite(id)
        return dao.fetchAllDataInFavorite()
    }
    
}