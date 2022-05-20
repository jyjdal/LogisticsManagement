@file:Suppress("DEPRECATION")

package com.example.logisticsmanagement.activity

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.logisticsmanagement.WAYBILL_URL_BASE
import com.example.logisticsmanagement.WAYBILL_TYPE_XML
import com.example.logisticsmanagement.data.OnlineWaybill
import com.example.logisticsmanagement.data.OnlineWaybillPackaged
import com.example.logisticsmanagement.data.OnlineWaybillService
import com.example.logisticsmanagement.ui.theme.LightBlue
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import kotlin.system.exitProcess

private const val logTagSuffix = "Online Waybill"

@Composable
fun OnlineWayBillActivity(navController: NavController, factoryType: String) {
    val factory =
        if (factoryType == WAYBILL_TYPE_XML)
            SimpleXmlConverterFactory.create()
        else JacksonConverterFactory.create()

    val waybillList = remember { mutableStateListOf<OnlineWaybill>() }
    LaunchedEffect(key1 = 1) {
        val retrofit =
            Retrofit.Builder().baseUrl(WAYBILL_URL_BASE).addConverterFactory(factory).build()
        val service = retrofit.create(OnlineWaybillService::class.java)
        val call =
            if (factoryType == WAYBILL_TYPE_XML)
                service.getWaybillsXml()
            else
                service.getWaybillsJson()
        call.enqueue(object : Callback<OnlineWaybillPackaged> {
            override fun onResponse(
                call: Call<OnlineWaybillPackaged>, response: Response<OnlineWaybillPackaged>
            ) {
                val list = response.body()!!.waybillList
                waybillList.addAll(list)
                "成功获取在线运单${factoryType}：%{list.size}".log(tagSuffix = logTagSuffix)
            }

            override fun onFailure(call: Call<OnlineWaybillPackaged>, t: Throwable) {
                "无法获取在线运单！类型：${factoryType}".log(level = Log.ERROR, tagSuffix = logTagSuffix)
                t.stackTraceToString().log(level = Log.ERROR, tagSuffix = logTagSuffix)
                exitProcess(-1)
            }

        })
    }

    Column(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxSize()
    ) {
        LazyColumn(modifier = Modifier.fillMaxHeight(0.9F)) {
            items(waybillList) { waybill ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 4.dp)
                        .border(1.dp, LightBlue, RoundedCornerShape(4.dp))
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(text = "${waybill.src}-${waybill.dest}", fontSize = 20.sp)
                        Text(text = "${waybill.name}:${waybill.count}", fontSize = 20.sp)
                        Text(text = waybill.waybillNo, fontSize = 20.sp)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = "收货人:${waybill.consignee}(${waybill.consigneePhoneNumber})",
                            fontSize = 20.sp
                        )
                        Text(
                            text = "到付${waybill.freightPaidByTheReceivingParty}元",
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.popBackStack() }) {
            Text(text = "返回")
        }
    }
}
