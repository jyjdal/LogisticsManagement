package com.example.logisticsmanagement.activity

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import kotlin.system.exitProcess

@Composable
fun ManageActivity(navController: NavController, jobNumber: String, password: String) {
    Column {
        Text(text = "物流管理系统 By 孙强")
        Divider()
        Text(text = "This is main activity content!")
        Button(onClick = { navController.popBackStack() }) { Text(text = "$jobNumber $password") }
        Button(onClick = { exitProcess(0) }) { Text(text = "退出") }
    }
}