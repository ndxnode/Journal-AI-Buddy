package com.example.journalaibuddy.ui.screens

import android.app.TimePickerDialog
import android.content.Context
import android.widget.TimePicker
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.journalaibuddy.viewmodel.SettingsViewModel
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(viewModel: SettingsViewModel) {
    var isDarkThemeEnabled by remember { mutableStateOf(false) }
    var areNotificationsEnabled by remember { mutableStateOf(false) }
    var selectedTime by remember { mutableStateOf(Calendar.getInstance().apply { set(Calendar.HOUR_OF_DAY, 22); set(Calendar.MINUTE, 0) }) }
    val context = LocalContext.current

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
                        if (areNotificationsEnabled) {
                            viewModel.setReminder(selectedTime)
                        } else {
                            viewModel.cancelReminder()
                        }
                    }
                )
            }
            if (areNotificationsEnabled) {
                Button(onClick = {
                    showTimePicker(context, selectedTime) { newTime ->
                        selectedTime = newTime
                        viewModel.setReminder(selectedTime)
                    }
                }) {
                    Text("Select Reminder Time")
                }
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

fun showTimePicker(context: Context, initialTime: Calendar, onTimeSelected: (Calendar) -> Unit) {
    val initialHour = initialTime.get(Calendar.HOUR_OF_DAY)
    val initialMinute = initialTime.get(Calendar.MINUTE)

    TimePickerDialog(context, { _: TimePicker, hour: Int, minute: Int ->
        val newTime = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
        }
        onTimeSelected(newTime)
    }, initialHour, initialMinute, true).show()
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
