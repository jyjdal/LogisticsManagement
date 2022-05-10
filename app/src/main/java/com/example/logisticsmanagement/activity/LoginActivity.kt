package com.example.logisticsmanagement.activity

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Assessment
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.logisticsmanagement.data.AppDatabase
import com.example.logisticsmanagement.ui.theme.LightBlue

@Composable
fun LoginActivity(navController: NavController) {
    val context = LocalContext.current

    // 用于保存登录的账号密码
    val jobNumber = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    // 用于显示对话框
    val showErrorDialog = remember { mutableStateOf(false) }
    val loginErrorMessage = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            Icons.TwoTone.Assessment,
            contentDescription = null,
            modifier = Modifier.size(200.dp),
            tint = LightBlue
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "物流管理系统", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(12.dp))
        TextField(
            value = jobNumber.value,
            onValueChange = { jobNumber.value = it },
            label = { Text(text = "用户名：") }
        )
        TextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text(text = "密码：") }
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = {
            // 首先进行判空
            if (jobNumber.value.isBlank() or password.value.isBlank()) {
                Toast.makeText(context, "用户名和密码不能为空！", Toast.LENGTH_SHORT).show()
                return@Button
            }

            // 从数据库中查找员工信息，进行比对
            val db = AppDatabase.getInstance(context)
            val employee = db.employeeDAO().findByJobNumber(jobNumber.value)

            // 首先判空
            if (employee == null) {
                showErrorDialog.value = true
                loginErrorMessage.value = "账号不存在！"
                return@Button
            }

            // 其次判断密码是否正确
            if (employee.password != password.value) {
                showErrorDialog.value = true
                loginErrorMessage.value = "密码错误！"
                return@Button
            }

            navController.navigate("main")
        }) {
            Text(text = "点击登陆.", fontSize = 20.sp)
        }
        LoginErrorAlertDialog(showDialog = showErrorDialog, loginErrorMessage)
    }
}

@Composable
fun LoginErrorAlertDialog(showDialog: MutableState<Boolean>, message: MutableState<String>) {
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text(text = "登录错误！") },
            text = { Text(text = message.value) },
            confirmButton = {
                TextButton(onClick = { showDialog.value = false }) {
                    Text(text = "确认")
                }
            }
        )
    }
}
