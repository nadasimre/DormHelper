package com.example.dormhelper.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dormhelper.model.DormPreset
import com.example.dormhelper.model.User

@Database(entities = [User::class, DormPreset::class], version = 13, exportSchema = false)
abstract class UserDatabase : RoomDatabase(){
    abstract val userDatabaseDao: UserDatabaseDAO

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user_database").fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}