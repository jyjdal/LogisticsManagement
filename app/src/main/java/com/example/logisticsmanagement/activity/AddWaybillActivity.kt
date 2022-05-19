package com.example.logisticsmanagement.activity

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import com.example.logisticsmanagement.FormInputType
import com.example.logisticsmanagement.data.AppDatabase
import com.example.logisticsmanagement.data.OfflineWaybill

@Composable
fun AddWaybillActivity(navController: NavController) {
    // 本地运单必选项
    val name = remember { mutableStateOf("") }
    val dest = remember { mutableStateOf("") }
    val count = remember { mutableStateOf("") }  // number

    // 本地运单可选项
    val consignor = remember { mutableStateOf("") }
    val consignorPhone = remember { mutableStateOf("") }
    val consignee = remember { mutableStateOf("") }
    val consigneePhone = remember { mutableStateOf("") }
    val prepayment = remember { mutableStateOf("") }  // number
    val toPay = remember { mutableStateOf("") }

    val context = LocalContext.current
    val dao = AppDatabase.getInstance(context).offlineWaybillDAO()

    fun clearWaybillFields() {
        name.value = ""
        dest.value = ""
        count.value = ""
        consignor.value = ""
        consignorPhone.value = ""
        consignee.value = ""
        consigneePhone.value = ""
        prepayment.value = ""
        toPay.value = ""
    }

    fun String.toIntEnhanced(): Int {
        return if (this.isBlank() or !(this.isDigitsOnly())) {
            0
        } else {
            this.toInt()
        }
    }

    fun constructWaybill(): OfflineWaybill {
        val waybill = OfflineWaybill()
        waybill.name = name.value
        waybill.dest = dest.value
        waybill.src = "沈阳"
        waybill.count = count.value.toIntEnhanced()
        waybill.consignor = consignor.value
        waybill.consignorPhoneNumber = consignorPhone.value
        waybill.consignee = consignee.value
        waybill.consigneePhoneNumber = consigneePhone.value
        waybill.freightPaidByConsignor = prepayment.value.toIntEnhanced()
        waybill.freightPaidByTheReceivingParty = toPay.value.toIntEnhanced()

        return waybill
    }

    Column {
        Column(
            modifier = Modifier
                .fillMaxHeight(0.9F)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row {
                FormInput(value = dest, label = "终点", required = true)
                Text(text = "起点：沈阳")
            }
            FormInput(value = name, label = "货物名称", required = true)
            FormInput(
                value = count,
                label = "数量",
                required = true,
                inputType = FormInputType.Number
            )
            FormInput(value = consignor, label = "发货人")
            FormInput(
                value = consignorPhone,
                label = "发货人电话",
                inputType = FormInputType.Phone
            )
            FormInput(value = consignee, label = "收货人")
            FormInput(
                value = consigneePhone,
                label = "收货人电话",
                inputType = FormInputType.Phone
            )
            FormInput(value = prepayment, label = "预付款", inputType = FormInputType.Number)
            FormInput(value = toPay, label = "到付款", inputType = FormInputType.Number)
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
                onClick = {
                    // construct waybill
                    val waybill = constructWaybill()
                    // add waybill to database
                    dao.insert(waybill)
                    // clear inputs
                    clearWaybillFields()
                }) {
                Text(text = "保存")
            }
        }
    }
}
