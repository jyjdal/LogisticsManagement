package com.example.logisticsmanagement.activity

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.logisticsmanagement.ROUTE_ADD_WAYBILL
import com.example.logisticsmanagement.data.AppDatabase
import com.example.logisticsmanagement.data.OfflineWaybill

@Composable
fun OfflineWaybillActivity(navController: NavController) {
    val context = LocalContext.current
    val waybillList = remember { mutableStateListOf<OfflineWaybill>() }

    LaunchedEffect(key1 = 1) {
        val db = AppDatabase.getInstance(context)
        waybillList.addAll(db.offlineWaybillDAO().getAll())
    }

    Column {
        LazyColumn(modifier = Modifier.fillMaxHeight(0.9F)) {
            items(waybillList) { waybill ->
                Text(text = waybill.name)
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