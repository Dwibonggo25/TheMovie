package com.example.jetpack

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetpack.db.User
import com.example.jetpack.db.UserDao

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}