package com.example.journalaibuddy.ui.screens

import android.app.Application
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.example.journalaibuddy.viewmodel.JournalViewModel
import com.example.journalaibuddy.viewmodel.SettingsViewModel
import com.example.journalaibuddy.viewmodel.SettingsViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenWithBottomNav(viewModel: JournalViewModel) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "main",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("main") { MainScreen(viewModel) }
            composable("calendar") { CalendarScreen(viewModel) }
            composable("settings") {
                val context = LocalContext.current
                // Explicitly specify the ViewModel class type
                val settingsViewModel = viewModel<SettingsViewModel>(
                    factory = SettingsViewModelFactory(context.applicationContext as Application)
                )
                SettingsScreen(settingsViewModel)
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Main") },
            label = { Text("Main") },
            selected = navController.currentDestination?.route == "main",
            onClick = { navController.navigate("main") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.DateRange, contentDescription = "Calendar") },
            label = { Text("Calendar") },
            selected = navController.currentDestination?.route == "calendar",
            onClick = { navController.navigate("calendar") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings") },
            label = { Text("Settings") },
            selected = navController.currentDestination?.route == "settings",
            onClick = { navController.navigate("settings") }
        )
    }
}
