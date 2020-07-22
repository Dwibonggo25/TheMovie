package com.example.jetpack

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetpack.db.Favorite
import com.example.jetpack.db.FavoriteDao

@Database(entities = [Favorite::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao
}