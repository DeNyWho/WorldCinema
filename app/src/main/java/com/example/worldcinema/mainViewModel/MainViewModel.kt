package com.example.worldcinema.mainViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.worldcinema.data.MovieList
import com.example.worldcinema.retrofit.ApiService
import com.example.worldcinema.retrofit.NetworkService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    var  recyclerListData: MutableLiveData<MovieList?> = MutableLiveData()

    fun getMovieList(): MutableLiveData<MovieList?>
    {
        return recyclerListData
    }

    fun getMovieData(filter: String){
        val retroInstance = NetworkService.getRetroInstance().create(ApiService::class.java)
        val call = retroInstance.getMovies(filter)
        call.enqueue(object : Callback<MovieList> {
            override fun onFailure(call: Call<MovieList>, t: Throwable) {
                recyclerListData.postValue( null)
                Log.i("TAG", t.message.toString())
            }
            override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                if (response.body() !=null){
                    recyclerListData.postValue(response.body())

                }else{
                    recyclerListData.postValue(null)
                    Log.d("TAG", response.raw().toString())
                }
            }
        })
    }

    fun getCoverData(){

    }
}