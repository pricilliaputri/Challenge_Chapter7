package com.punyacile.challenge_chapter6.network

import com.punyacile.challenge_chapter6.model.MoviePopular
import retrofit2.Call
import retrofit2.http.GET

interface RestfulAPI {

    @GET("movie/popular?api_key=9bee349cfeda097c0b57c7b6d1b6aa2f&page=1")
    fun getPopularMovie(): Call<MoviePopular>
}