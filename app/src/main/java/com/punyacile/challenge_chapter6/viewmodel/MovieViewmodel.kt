package com.punyacile.challenge_chapter6.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.punyacile.challenge_chapter6.model.MoviePopular
import com.punyacile.challenge_chapter6.model.Result
import com.punyacile.challenge_chapter6.network.RestfulAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MovieViewmodel @Inject constructor(private var api: RestfulAPI) : ViewModel() {
    var liveDataMovie: MutableLiveData<List<Result>?> = MutableLiveData()

    fun getMovie() {
        api.getPopularMovie().enqueue(object : Callback<MoviePopular> {
            override fun onResponse(
                call: Call<MoviePopular>,
                response: Response<MoviePopular>
            ) {
                if (response.isSuccessful) {
                    liveDataMovie.postValue(response.body()?.results)
                } else {
                    liveDataMovie.postValue(null)
                }
            }

            override fun onFailure(call: Call<MoviePopular>, t: Throwable) {
                liveDataMovie.postValue(null)
            }

        })
    }
}