package com.example.worldcinema.retrofit

import com.example.worldcinema.model.Auth
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @POST("auth/register")
    @FormUrlEncoded
    suspend fun signUp(@Field("email") email: String, @Field("password") password: String, @Field("firstName") firstName: String, @Field("lastName") lastName: String): Response<Auth>

    @POST("auth/login")
    @FormUrlEncoded
    suspend fun signIn(@Field("email") email: String, @Field("password") password: String): Response<Auth>
}