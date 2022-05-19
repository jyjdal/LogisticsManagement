package com.example.logisticsmanagement.activity

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.logisticsmanagement.*
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

import kotlin.system.exitProcess

@Composable
fun ManageActivity(navController: NavController, jobNumber: String, password: String) {
    val showDialog = remember { mutableStateOf(false) }

    val context = LocalContext.current
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(GITHUB_REPO)

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            TextButton(onClick = { showDialog.value = true }) {
                Text(text = "物流管理系统 By 孙强", textAlign = TextAlign.Start)
            }
        }
        ContentDivider()

        val currentTime = remember { mutableStateOf("") }
        // 设置定时任务用于更新时间
        LaunchedEffect(key1 = currentTime.value) {
            // 这里不能小于1000毫秒，否则无法更新时间
            delay(1000)
            val format = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.CHINA)
            val calendar = Calendar.getInstance()
            currentTime.value = format.format(calendar.time)
        }

        // 展示工号、密码和当前时间
        Column {
            FieldDisplayRow(label = "工    号：", field = jobNumber)
            FieldDisplayRow(label = "密    码：", field = password)
            FieldDisplayRow(label = "当前时间：", field = currentTime.value)
        }
        ContentDivider()

        Column(
            modifier = Modifier
                .fillMaxWidth(1F)
                .fillMaxHeight(0.6F),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { navController.navigate(ROUTE_OFFLINE_WAYBILL) }) {
                Text(text = "查看本地运单")
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { navController.navigate("${ROUTE_ONLINE_WAYBILL}/${WAYBILL_TYPE_JSON}") }) {
                Text(text = "查看在线运单-Json")
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { navController.navigate("${ROUTE_ONLINE_WAYBILL}/${WAYBILL_TYPE_XML}") }) {
                Text(text = "查看在线运单-Xml")
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { navController.popBackStack() }) { Text(text = "退出登录") }
            Button(onClick = { exitProcess(0) }) { Text(text = "退出") }
        }
    }
    CustomContentDialog(showDialog = showDialog, title = "物流管理系统 v1.0") {
        Column {
            Text(
                text = """
            作者：孙强
            学号：20194780
            班级：计算机1906
        """.trimIndent(), fontSize = PRIMARY_TEXT_SIZE
            )
            TextButton(onClick = { context.startActivity(intent) }) {
                Text(
                    text = "GitHub代码仓库", fontSize = SECONDARY_TEXT_SIZE,
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    }
}

@Composable
fun ContentDivider() {
    Divider(
        modifier = Modifier
            .padding(0.dp, 30.dp)
            .height(3.dp)
    )
}

// 每行垂直方向上的padding
val ROW_VERTICAL_PADDING = 20.dp

// 每行标签和内容的宽度占比，实际超过1
const val LABEL_WIDTH = 0.35F
const val FIELD_WIDTH = 1.1F

// 字体大小
val LABEL_FONT_SIZE = 24.sp
val FIELD_FONT_SIZE = 20.sp

@Composable
fun FieldDisplayRow(label: String, field: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(0.dp, ROW_VERTICAL_PADDING)
    ) {
        Text(
            text = label, fontSize = LABEL_FONT_SIZE, textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth(LABEL_WIDTH),
        )
        OutlinedTextField(
            value = field, onValueChange = {},
            readOnly = true, modifier = Modifier.fillMaxWidth(FIELD_WIDTH),
            textStyle = TextStyle(fontSize = FIELD_FONT_SIZE),
        )
    }
}
