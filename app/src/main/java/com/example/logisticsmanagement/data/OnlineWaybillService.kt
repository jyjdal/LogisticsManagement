package com.example.logisticsmanagement.data

import com.example.logisticsmanagement.WAYBILL_URL_JSON
import com.example.logisticsmanagement.WAYBILL_URL_XML
import retrofit2.Call
import retrofit2.http.GET

interface OnlineWaybillService {
    @GET(WAYBILL_URL_JSON)
    fun getWaybillsJson(): Call<OnlineWaybillPackaged>

    @GET(WAYBILL_URL_XML)
    fun getWaybillsXml(): Call<OnlineWaybillPackaged>
}