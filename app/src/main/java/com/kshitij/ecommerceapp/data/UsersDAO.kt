package com.kshitij.ecommerceapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kshitij.ecommerceapp.data.model.Users

@Dao
interface UsersDAO {
    @Insert
    suspend fun insert(users: Users)

    @Query("SELECT * FROM users_table WHERE username LIKE :username")
    suspend fun getUsername(username: String): Users?
}