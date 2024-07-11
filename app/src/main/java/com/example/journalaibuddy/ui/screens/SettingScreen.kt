package com.example.journalaibuddy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.journalaibuddy.viewmodel.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(viewModel: SettingsViewModel) {
    var isDarkThemeEnabled by remember { mutableStateOf(false) }
    var areNotificationsEnabled by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Settings") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text("Hi, Name", style = MaterialTheme.typography.titleMedium)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Enable Dark Theme")
                Switch(
                    checked = isDarkThemeEnabled,
                    onCheckedChange = {
                        isDarkThemeEnabled = it
                        viewModel.toggleTheme(isDarkThemeEnabled)
                    }
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Notifications")
                Switch(
                    checked = areNotificationsEnabled,
                    onCheckedChange = {
                        areNotificationsEnabled = it
                        viewModel.toggleNotifications(areNotificationsEnabled)
                    }
                )
            }

            Button(
                onClick = { viewModel.enableAppLock() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Filled.Lock, contentDescription = "Lock App")
                Spacer(Modifier.width(8.dp))
                Text("Lock App with Passcode")
            }

            Button(
                onClick = { viewModel.logOut() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Filled.ExitToApp, contentDescription = "Log Out")
                Spacer(Modifier.width(8.dp))
                Text("Log Out")
            }
        }
    }
}

class SettingsViewModel {
    fun toggleTheme(isDark: Boolean) {
        // Toggle the theme setting
    }

    fun toggleNotifications(enabled: Boolean) {
        // Toggle the notifications setting
    }

    fun enableAppLock() {
        // Logic to enable app lock
    }

    fun logOut() {
        // Logic for logging out
    }
}
