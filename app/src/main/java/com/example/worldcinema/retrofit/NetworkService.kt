package com.example.worldcinema.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

private const val baseURL = "http://cinema.areas.su/"

class NetworkService {
//    companion object{
//        fun api(): ApiService {
//            return Retrofit.Builder()
//                .baseUrl(baseURL)
//                .addConverterFactory(MoshiConverterFactory.create())
//                .build()
//                .create(ApiService::class.java)
//        }
//    }
companion object {
    fun getRetroInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun api(): ApiService {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
}