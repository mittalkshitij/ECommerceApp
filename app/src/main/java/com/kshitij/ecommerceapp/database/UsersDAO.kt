package com.kshitij.ecommerceapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsersDAO {

    @Insert
    suspend fun insert(users: Users)

    @Query("SELECT * FROM users_table WHERE username LIKE :username")
    suspend fun getUsername(username: String): Users?



}