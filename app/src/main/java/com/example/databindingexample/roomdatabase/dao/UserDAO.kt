package com.example.databindingexample.roomdatabase.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.databindingexample.models.UserDetails

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(vararg user: UserDetails)

    @Query("SELECT * FROM UserDetails")
    fun getUsers(): LiveData<List<UserDetails>>
}