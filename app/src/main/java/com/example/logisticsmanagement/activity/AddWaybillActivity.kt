package com.example.logisticsmanagement.activity

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import com.example.logisticsmanagement.data.AppDatabase
import com.example.logisticsmanagement.data.OfflineWaybill

@Composable
fun AddWaybillActivity(navController: NavController) {
    val waybill = remember { mutableStateOf(OfflineWaybill()) }
    initWaybill(waybill)
    val count = remember { mutableStateOf("") }

    val context = LocalContext.current
    val dao = AppDatabase.getInstance(context).offlineWaybillDAO()

    Column {
        Column(
            modifier = Modifier
                .fillMaxHeight(0.9F)
                .fillMaxWidth()
        ) {
            Row {
                Text(text = "终点：")
                TextField(value = waybill.value.dest, onValueChange = { waybill.value.dest = it })
                Text(text = "起点：沈阳")
            }
            Row {
                Text(text = "货物名称：")
                TextField(
                    value = waybill.value.name,
                    onValueChange = { waybill.value.name = it })
            }
            Row {
                OutlinedTextField(
                    value = count.value,
                    onValueChange = {
                        waybill.value.count =
                            if (it.isDigitsOnly() and it.isNotBlank()) it.toInt() else 0
                        count.value = it
                    },
                    isError = count.value.isBlank() or !(count.value.isDigitsOnly()),
                    label = { Text(text = "数量") }
                )
            }
            Row {
                Text(text = "发货人：")
                TextField(
                    value = waybill.value.consignor,
                    onValueChange = { waybill.value.consignor = it })
            }
            Row {
                Text(text = "发货人电话：")
                TextField(
                    value = waybill.value.consignorPhoneNumber,
                    onValueChange = { waybill.value.consignorPhoneNumber = it })
            }
            Row {
                Text(text = "收货人：")
                TextField(
                    value = waybill.value.consignee,
                    onValueChange = { waybill.value.consignee = it })
            }
            Row {
                Text(text = "收货人电话：")
                TextField(
                    value = waybill.value.consigneePhoneNumber,
                    onValueChange = { waybill.value.consigneePhoneNumber = it })
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
                onClick = { dao.insert(waybill.value) }) {
                Text(text = "保存")
            }
        }
    }
}

private fun initWaybill(waybill: MutableState<OfflineWaybill>) {
    waybill.value = OfflineWaybill()
    waybill.value.src = "沈阳"
}
