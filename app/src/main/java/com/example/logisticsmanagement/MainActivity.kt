package com.example.logisticsmanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.logisticsmanagement.activity.LoginActivity
import com.example.logisticsmanagement.activity.ManageActivity
import com.example.logisticsmanagement.activity.ROUTE_LOGIN
import com.example.logisticsmanagement.activity.ROUTE_MAIN
import com.example.logisticsmanagement.ui.theme.ApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplicationTheme {
                MainContent()
            }
        }
    }
}

@Preview
@Composable
fun MainContent() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ROUTE_LOGIN) {
        composable(ROUTE_LOGIN) { LoginActivity(navController = navController) }
        composable(ROUTE_MAIN) { ManageActivity(navController = navController) }
    }
}
