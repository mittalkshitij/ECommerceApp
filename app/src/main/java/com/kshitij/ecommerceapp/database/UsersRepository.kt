package com.kshitij.ecommerceapp.database

import android.util.Log

class UsersRepository(private val dao: UsersDAO) {


    suspend fun insert(user: Users) {
        return dao.insert(user)
    }

    suspend fun getUserName(username: String): Users?{
        return dao.getUsername(username)
    }


}