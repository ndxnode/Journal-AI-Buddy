package com.example.journalaibuddy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.journalaibuddy.database.isFirstLaunch
import com.example.journalaibuddy.database.setFirstLaunch
import com.example.journalaibuddy.ui.screens.MainScreen
import com.example.journalaibuddy.ui.screens.WelcomeScreen
import com.example.journalaibuddy.ui.screens.WelcomeViewModel
import com.example.journalaibuddy.viewmodel.JournalViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

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
    val context = LocalContext.current
    var isFirstLaunch by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        isFirstLaunch = isFirstLaunch(context).first()
    }

    NavHost(navController, startDestination = if (isFirstLaunch) "welcome" else "main") {
        composable("welcome") {
            val viewModel: WelcomeViewModel = viewModel()
            WelcomeScreen(viewModel = viewModel, onLoginSuccess = {
                navController.navigate("main") {
                    popUpTo("welcome") { inclusive = true }
                }
                coroutineScope.launch {
                    setFirstLaunch(context, false)
                }
            })

        }
        composable("main") {
            val viewModel: JournalViewModel = viewModel()
            MainScreen(viewModel)
        }
    }
}