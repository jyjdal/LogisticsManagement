package com.example.logisticsmanagement.activity

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.logisticsmanagement.ROUTE_ADD_WAYBILL
import com.example.logisticsmanagement.data.AppDatabase
import com.example.logisticsmanagement.data.OfflineWaybill
import com.example.logisticsmanagement.ui.theme.LightBlue

@Composable
fun OfflineWaybillActivity(navController: NavController) {
    val context = LocalContext.current
    val waybillList = remember { mutableStateListOf<OfflineWaybill>() }

    LaunchedEffect(key1 = 1) {
        val db = AppDatabase.getInstance(context)
        waybillList.addAll(db.offlineWaybillDAO().getAll())
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
                        Text(text = waybill.name, fontSize = 20.sp)
                        Text(text = "数量: ${waybill.count}", fontSize = 20.sp)
                        Text(
                            text = "到付: ${waybill.freightPaidByTheReceivingParty}元",
                            fontSize = 20.sp
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(text = "收货人：${waybill.consignee}", fontSize = 20.sp)
                        Text(text = "电话: ${waybill.consigneePhoneNumber}", fontSize = 20.sp)
                    }
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(0.5F),
                onClick = { navController.popBackStack() }) {
                Text(text = "返回")
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { navController.navigate(ROUTE_ADD_WAYBILL) }) {
                Text(text = "添加")
            }
        }
    }
}