package com.example.databindingexample.roomdatabase.dao.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.databindingexample.models.UserDetails
import com.example.databindingexample.roomdatabase.dao.UserDAO

@Database(entities = [UserDetails::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }   
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "UserDatabase"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}