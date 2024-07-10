package com.example.journalaibuddy.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(viewModel: SettingsViewModel) {
    var isDarkThemeEnabled by remember { mutableStateOf(false) }
    var areNotificationsEnabled by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Settings") })
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text("Hi, Name", style = MaterialTheme.typography.titleMedium)
            // get name from firebase

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
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp)
            ) {
                Icon(Icons.Filled.Lock, contentDescription = "Lock App")
                Spacer(Modifier.width(8.dp))
                Text("Lock App with Biometrics/Passcode")
            }

            Button(
                onClick = { viewModel.logOut() },
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp)
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
