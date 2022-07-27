package com.wordyka.retrofitapi

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("everything?q=tesla&from=2022-06-27&sortBy=publishedAt&apiKey=3202f7abcee84c0a9ff08eeb07ab7977")
    fun getData(): Call<MainModel>
}