package com.example.logisticsmanagement.data

import retrofit2.Call
import retrofit2.http.GET

interface WaybillApi {
    @GET("")
    fun getWaybills(): Call<List<Waybill>>
}