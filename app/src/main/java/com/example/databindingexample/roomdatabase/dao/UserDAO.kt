package com.example.databindingexample.roomdatabase.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.databindingexample.models.UserDetails

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(vararg user: UserDetails)

    @Transaction
    @Query("SELECT * FROM UserDetails WHERE username =:name")
    fun getUsers(name: String): LiveData<List<UserDetails>>
}