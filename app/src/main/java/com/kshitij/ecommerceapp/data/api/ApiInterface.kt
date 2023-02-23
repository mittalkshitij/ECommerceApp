package com.kshitij.ecommerceapp.data.api

import com.kshitij.ecommerceapp.ui.dashboard.model.Items
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface{

    @GET("products")
    fun getItems() : Call<List<Items>>
}