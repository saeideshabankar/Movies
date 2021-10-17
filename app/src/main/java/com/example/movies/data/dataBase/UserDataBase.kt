package com.example.movies.data.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movies.data.models.FavData
import com.example.movies.data.models.User

@Database(entities = [User::class, FavData::class], version = 2, exportSchema = false)
abstract class UserDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun favDao(): FavDao
}
