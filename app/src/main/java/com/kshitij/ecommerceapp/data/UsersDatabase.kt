package com.kshitij.ecommerceapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kshitij.ecommerceapp.data.model.Users


@Database(entities = [Users::class], version = 1, exportSchema = false)
abstract class UsersDatabase : RoomDatabase() {

    abstract val usersDatabaseDao: UsersDAO

    companion object {

        @Volatile
        private var INSTANCE: UsersDatabase? = null


        fun getInstance(context: Context): UsersDatabase {
            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UsersDatabase::class.java,
                        "user_details_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}