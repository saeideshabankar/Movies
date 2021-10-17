package com.example.movies.data.dataBase

import androidx.room.*
import com.example.movies.data.models.User
import com.example.movies.utils.DATABASE_USER_TABLE

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("SELECT * FROM $DATABASE_USER_TABLE WHERE numberOrEmail LIKE :numberTv")
    fun getUser(numberTv: String): User

    @Query("SELECT * FROM $DATABASE_USER_TABLE WHERE numberOrEmail=(:numberOrEmailTv) and password=(:password)")
    fun getUserInfo(numberOrEmailTv: String, password: String): User

    @Query("SELECT * FROM $DATABASE_USER_TABLE")
    fun getAllPeople(): MutableList<User>

    @Query("DELETE FROM $DATABASE_USER_TABLE")
    fun deleteAllPeople()

}