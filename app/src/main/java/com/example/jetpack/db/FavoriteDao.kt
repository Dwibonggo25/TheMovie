package com.example.jetpack.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserData (fund: Favorite )

    @Query("DELETE From FAVORITE where id =:movieId")
    suspend fun deleteDataInFavorite (movieId: Long)

    @Query("SELECT COUNT(*) From favorite where id =:movieId and type =1 ")
    suspend fun fetchDataInFavorite (movieId: Long) : Long

    @Query("SELECT * from favorite where type = 1")
    suspend fun fetchAllDataInFavorite() : List<Favorite>
}