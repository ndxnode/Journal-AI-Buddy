package com.example.journalaibuddy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.journalaibuddy.ui.screens.MainScreen
import com.example.journalaibuddy.ui.screens.WelcomeScreen
import com.example.journalaibuddy.ui.screens.WelcomeViewModel
import com.example.journalaibuddy.viewmodel.JournalViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavigation()
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val isFirstLaunch = remember { isFirstLaunch() }

    NavHost(navController, startDestination = if (isFirstLaunch) "welcome" else "main") {
        composable("welcome") {
            val viewModel: WelcomeViewModel = viewModel()
            WelcomeScreen(viewModel = viewModel, onLoginSuccess = { navController.navigate("main") })
        }
        composable("main") {
            val viewModel: JournalViewModel = viewModel()
            MainScreen(viewModel)
        }
    }
}

fun isFirstLaunch(): Boolean {
    return true
}