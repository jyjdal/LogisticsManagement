package com.example.logisticsmanagement.activity

import android.widget.Toast
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.twotone.Assessment
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.logisticsmanagement.ROUTE_MAIN
import com.example.logisticsmanagement.data.AppDatabase
import com.example.logisticsmanagement.ui.theme.LightBlue
import kotlin.system.exitProcess

@Composable
fun LoginActivity(navController: NavController) {
    val context = LocalContext.current

    // 用于保存登录的账号密码
    val jobNumber = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    // 用于显示对话框
    val showErrorDialog = remember { mutableStateOf(false) }
    val loginErrorMessage = remember { mutableStateOf("") }
    // 用于获取控件焦点
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Icon(
            Icons.TwoTone.Assessment, contentDescription = null,
            modifier = Modifier.size(140.dp), tint = LightBlue
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "物流管理系统 By 孙强", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = jobNumber.value, onValueChange = { jobNumber.value = it },
            label = { Text(text = "工号：", fontSize = 20.sp) },
            leadingIcon = { Icon(Icons.Filled.Person, contentDescription = null) },
            trailingIcon = {
                if (jobNumber.value.isNotEmpty()) {
                    IconButton(onClick = { jobNumber.value = "" }) {
                        Icon(
                            Icons.Filled.Clear,
                            contentDescription = null
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { focusManager.moveFocus(FocusDirection.Down) })
        )
        Spacer(modifier = Modifier.size(24.dp))
        OutlinedTextField(
            value = password.value, onValueChange = { password.value = it },
            label = { Text(text = "密码：", fontSize = 20.sp) },
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = { Icon(Icons.Filled.Password, contentDescription = null) },
            trailingIcon = {
                if (password.value.isNotEmpty()) {
                    IconButton(onClick = { password.value = "" }) {
                        Icon(
                            Icons.Filled.Clear,
                            contentDescription = null
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { focusManager.moveFocus(FocusDirection.Down) })
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Button(onClick = {
                if (jobNumber.value.isBlank() or password.value.isBlank()) {
                    Toast.makeText(context, "用户名或密码不能为空！", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                // 从数据库中查找员工信息，进行比对
                val db = AppDatabase.getInstance(context)
                val employee = db.employeeDAO().findByJobNumber(jobNumber.value)

                // 如果账号不存在
                if (employee == null) {
                    showErrorDialog.value = true
                    loginErrorMessage.value = "账号不存在！"
                    return@Button
                }

                // 如果密码不正确
                if (employee.password != password.value) {
                    // 清空输入框
                    jobNumber.value = ""
                    password.value = ""
                    // 显示错误提示对话框
                    showErrorDialog.value = true
                    loginErrorMessage.value = "密码错误！"
                    return@Button
                }

                // 正确登录，首先清空输入，然后导航到
                navController.navigate("${ROUTE_MAIN}/${jobNumber.value}/${password.value}")
                jobNumber.value = ""
                password.value = ""
            }, modifier = Modifier.focusable()) { Text(text = "登陆", fontSize = 24.sp) }
            Spacer(modifier = Modifier.width(64.dp))
            Button(onClick = { exitProcess(0) }) { Text(text = "退出", fontSize = 24.sp) }
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
