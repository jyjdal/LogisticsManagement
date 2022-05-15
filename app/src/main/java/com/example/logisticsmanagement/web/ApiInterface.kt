package com.example.logisticsmanagement.web

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("1w")
    fun getData(): Call<WebData>
}