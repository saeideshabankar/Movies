package com.example.movies.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movies.utils.DATABASE_USER_TABLE

@Entity(tableName = DATABASE_USER_TABLE)
data class User(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "lastName") var lastName: String,
    @ColumnInfo(name = "numberOrEmail") var numberOrEmail: String,
    @PrimaryKey
    @ColumnInfo(name = "password") var password: String,
    @ColumnInfo(name = "gender") var gender: String,
)
