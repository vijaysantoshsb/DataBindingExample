package com.example.databindingexample.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["username"], unique = true)])
data class UserDetails(
    @PrimaryKey(autoGenerate = true) val uid: Int? = null,
    @ColumnInfo(name = "username") var username: String?,
    @ColumnInfo(name = "password") var password: String?,
    @ColumnInfo(name = "mobile") var mobile: String?
)