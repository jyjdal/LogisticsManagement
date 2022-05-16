@file:Suppress("DEPRECATION")

package com.example.logisticsmanagement.activity

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.logisticsmanagement.WAYBILL_URL_BASE
import com.example.logisticsmanagement.WAYBILL_TYPE_XML
import com.example.logisticsmanagement.data.Waybill
import com.example.logisticsmanagement.data.WaybillPackaged
import com.example.logisticsmanagement.data.WaybillService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

@Composable
fun OnlineWayBill(navController: NavController, factoryType: String) {
    val factory =
        if (factoryType == WAYBILL_TYPE_XML)
            SimpleXmlConverterFactory.create()
        else JacksonConverterFactory.create()

    val dataList = remember { mutableStateListOf<Int>() }
    val waybillList = remember { mutableStateListOf<Waybill>() }
    LaunchedEffect(key1 = 1) {
        val retrofit =
            Retrofit.Builder().baseUrl(WAYBILL_URL_BASE).addConverterFactory(factory).build()
        val service = retrofit.create(WaybillService::class.java)
        val call =
            if (factoryType == WAYBILL_TYPE_XML)
                service.getWaybillsXml()
            else
                service.getWaybillsJson()
        call.enqueue(object : Callback<WaybillPackaged> {
            override fun onResponse(call: Call<WaybillPackaged>, response: Response<WaybillPackaged>) {
                val list = response.body()!!.waybillList
                Log.i("Web waybill", "${list.size}")
                waybillList.addAll(list)
                "${waybillList.size}".log()
            }

            override fun onFailure(call: Call<WaybillPackaged>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    Column {
        LazyColumn(modifier = Modifier.fillMaxHeight(0.9F)) {
            for (data in dataList) {
                item { Text(text = "$data") }
            }
        }
        Button(modifier = Modifier.fillMaxHeight(0.1F), onClick = {
            dataList.add(dataList.size)
        }) {
            Text(text = "click me")
        }
    }
}
