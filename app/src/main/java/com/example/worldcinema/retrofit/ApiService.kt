package com.example.worldcinema.retrofit

import android.database.Observable
import com.example.worldcinema.data.LastVideoInfo
import com.example.worldcinema.data.MovieGson
import com.example.worldcinema.data.MovieList
import com.example.worldcinema.model.Auth
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import java.util.*

interface ApiService {

    @POST("auth/register")
    @FormUrlEncoded
    suspend fun signUp(@Field("email") email: String, @Field("password") password: String, @Field("firstName") firstName: String, @Field("lastName") lastName: String): Response<Auth>

    @POST("auth/login")
    @FormUrlEncoded
    suspend fun signIn(@Field("email") email: String, @Field("password") password: String): Response<Auth>

    @GET("movies/cover")
    suspend fun getCover(): Response<MovieGson>

    @GET("movies")
    fun getMovies(@Query("filter")filter: String): Call<MovieList>

    @GET("usermovies")
    fun getLastVideo(
        @Query("filter") filter: String): Observable<List<LastVideoInfo>>
}