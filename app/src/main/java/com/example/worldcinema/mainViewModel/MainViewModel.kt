package com.example.worldcinema.mainViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.worldcinema.data.EpisodeInfo
import com.example.worldcinema.data.MovieList
import com.example.worldcinema.data.MoviesListItem
import com.example.worldcinema.retrofit.ApiService
import com.example.worldcinema.retrofit.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    var  recyclerListData: MutableLiveData<MovieList?> = MutableLiveData()

    //episodes
    private val _episodesInfo = MutableLiveData<List<EpisodeInfo>?>(null)
    val episodeInfo = _episodesInfo

    fun getMovieList(): MutableLiveData<MovieList?>
    {
        return recyclerListData
    }

//    fun downloadInfoEpisodesRequiredMovie(movieId: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            _episodesInfo.value = downloadInfoRequiredMovie(movieId)
//        }
//    }
//
//    private fun downloadInfoRequiredMovie(movieId: String) {
//        val retroInstance = NetworkService.getRetroInstance().create(ApiService::class.java)
//        val call = retroInstance.downloadInfoRequiredMovie(movieId)
//        call.enqueue(object : Callback<MoviesListItem> {
//
//            override fun onFailure(call: Call<MoviesListItem>, t: Throwable) {
//                episodeInfo.postValue(null)
//                Log.i("TAG", t.message.toString())
//            }
//            override fun onResponse(call: Call<MoviesListItem>, response: Response<MoviesListItem>) {
//                if (response.body() !=null){
//                    episodeInfo.postValue(response.body())
//
//                }else{
//                    episodeInfo.postValue(null)
//                    Log.d("TAG", response.raw().toString())
//                }
//            }
//        })
//    }

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