package com.example.jetpack.db

import androidx.room.*
import io.reactivex.Single

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserData (fund: Favorite )

    @Query("SELECT COUNT(*) From favorite where id =:movieId and type =1 ")
    suspend fun fetchDataInFavorite (movieId: Long) : Long

    @Query("SELECT * from favorite where type = 1")
    suspend fun fetchAllDataInFavorite() : List<Favorite>

    @Query ("UPDATE favorite SET type = 0 where id =:id")
    suspend fun deleteDataInFavorite(id: Int)
}