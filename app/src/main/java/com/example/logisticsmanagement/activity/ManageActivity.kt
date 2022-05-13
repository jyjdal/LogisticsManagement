package com.example.logisticsmanagement.activity

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

import kotlin.system.exitProcess

@Composable
fun ManageActivity(navController: NavController, jobNumber: String, password: String) {
    val showDialog = remember { mutableStateOf(false) }

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

        Row {
            Button(onClick = { navController.popBackStack() }) { Text(text = "退出登录") }
            Spacer(modifier = Modifier.width(80.dp))
            Button(onClick = { exitProcess(0) }) { Text(text = "退出") }
        }
    }
    CustomContentDialog(showDialog = showDialog, title = "物流管理系统 v1.0") {
        Column {
            Text(
                text = """
            物流管理系统 v1.0 By 孙强
            学号：20194780
            班级：计算机1906
        """.trimIndent(), fontSize = PRIMARY_TEXT_SIZE
            )
            TextButton(onClick = {

            }) {
                Text(text = "Github仓库", fontSize = PRIMARY_TEXT_SIZE)
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
const val LABEL_WIDTH = 0.3F
const val FIELD_WIDTH = 0.9F

// 字体大小
val LABEL_FONT_SIZE = 28.sp
val FIELD_FONT_SIZE = 24.sp

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
