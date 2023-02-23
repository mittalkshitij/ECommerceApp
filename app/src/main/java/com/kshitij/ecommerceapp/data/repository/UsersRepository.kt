package com.kshitij.ecommerceapp.data.repository

import com.kshitij.ecommerceapp.data.UsersDAO
import com.kshitij.ecommerceapp.data.model.Users


class UsersRepository(val dao: UsersDAO) {


    suspend fun insert(user: Users) {
        return dao.insert(user)
    }

    suspend fun getUserName(username: String): Users?{
        return dao.getUsername(username)
    }

//    suspend fun getUserDetails(userId : Int) : Users?{
//        return dao.getUserData(userId)
//    }
}