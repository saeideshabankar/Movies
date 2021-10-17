package com.example.movies.data.dataBase

import androidx.room.*
import com.example.movies.data.models.FavData
import com.example.movies.utils.DATABASE_FAV_TABLE

@Dao
interface FavDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFav(favData: FavData)

    @Update
    fun updateFav(favData: FavData)

    @Delete
    fun deleteFav(favData: FavData)

    @Query("SELECT * FROM $DATABASE_FAV_TABLE WHERE title LIKE :title")
    fun getFav(title: String): FavData

    @Query("SELECT * FROM $DATABASE_FAV_TABLE WHERE movie_id LIKE :movieId")
    fun checkFavById(movieId: Int?): FavData

    @Query("DELETE FROM $DATABASE_FAV_TABLE WHERE movie_id LIKE :movieId")
    fun deleteFavById(movieId: Int?)

    @Query("SELECT * FROM $DATABASE_FAV_TABLE")
    fun getAllFav(): MutableList<FavData>

    @Query("DELETE FROM $DATABASE_FAV_TABLE")
    fun deleteAllFavs()

}