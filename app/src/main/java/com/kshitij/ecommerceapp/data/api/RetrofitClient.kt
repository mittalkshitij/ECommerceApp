package com.kshitij.ecommerceapp.dashboard

import com.kshitij.ecommerceapp.data.api.ApiInterface
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//interface ApiInterface{
//
//    @GET("products")
//    fun getItems() : Call<List<Items>>
//}

class RetrofitClient {

    companion object{

        var myRetrofit = Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }
}