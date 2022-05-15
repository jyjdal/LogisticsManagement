package com.example.logisticsmanagement.activity

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.example.logisticsmanagement.WAYBILL_TYPE_XML
import com.example.logisticsmanagement.web.ApiInterface
import com.example.logisticsmanagement.web.WebData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.converter.jaxb.JaxbConverterFactory

//LaunchedEffect(key1 = 1) {
//    val retrofit = Retrofit.Builder()
//        .baseUrl("https://sdfsdf.dev/")
//        .addConverterFactory(JacksonConverterFactory.create())
//        .build()
//    val service = retrofit.create(ApiInterface::class.java)
//    service.getData().enqueue(object : Callback<WebData> {
//        override fun onResponse(
//            call: Call<WebData>,
//            response: Response<WebData>
//        ) {
//            val list = response.body()
//            Log.i("Web", "${list?.data}")
//            data.value = list?.data.toString()
//        }
//
//        override fun onFailure(call: Call<WebData>, t: Throwable) {
//            t.printStackTrace()
//        }
//    })
//}

@Composable
fun OnlineWayBill(navController: NavController, factoryType: String) {
    val factory =
        if (factoryType == WAYBILL_TYPE_XML)
            JaxbConverterFactory.create()
        else JacksonConverterFactory.create()

    val dataList = remember { mutableStateListOf<Int>() }
    Column {
        LazyColumn {
            for (data in dataList) {
                item { Text(text = "$data") }
            }
        }
        Button(onClick = {
            dataList.add(dataList.size)
        }) {
            Text(text = "click me")
        }
    }
}
