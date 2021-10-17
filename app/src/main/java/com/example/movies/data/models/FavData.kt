package com.example.movies.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movies.utils.DATABASE_FAV_TABLE

@Entity(tableName = DATABASE_FAV_TABLE)
data class FavData(
    @PrimaryKey
    @ColumnInfo(name = "movie_id") var movieId: Int?,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "adult") var adult: String,
    @ColumnInfo(name = "language") var language: String,
    @ColumnInfo(name = "about") var about: String,
    @ColumnInfo(name = "releaseData") var releaseData: String,
    @ColumnInfo(name = "popularity") var popularity: String
) {
}
