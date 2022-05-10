package com.example.logisticsmanagement.activity

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun ManageActivity(navController: NavController) {
    Text(text = "This is main activity content!")
    Button(onClick = { navController.navigate("login") }) {
        Text(text = "Click me to get back.")
    }
}