package com.example.journalaibuddy.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.pager.*
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen(viewModel: WelcomeViewModel, onLoginSuccess: () -> Unit) {
    val pageCount = 4
    val pagerState = rememberPagerState(pageCount = { pageCount })

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        when (page) {
            0 -> IntroductionPage()
            1 -> NotificationSettingsPage(viewModel)
            2 -> LockJournalPage(viewModel)
            3 -> LoginScreen(onLoginSuccess)
        }
    }
}

@Composable
fun IntroductionPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Welcome to Journal",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            "Write about your daily life and add photos, places, and more.\n\n" +
                    "Lock your journal to keep it private.\n\n" +
                    "Schedule time for writing and make it a habit.",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 16.dp)
        )
        Button(
            onClick = { /* Navigate to next page or action */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {
            Text("Continue", color = Color.White, fontSize = 18.sp)
        }
    }
}

@Composable
fun NotificationSettingsPage(viewModel: WelcomeViewModel) {
    var enableNotifications by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text("Enable Notifications")
        Switch(
            checked = enableNotifications,
            onCheckedChange = { enableNotifications = it }
        )
        Button(onClick = { viewModel.enableNotifications() }) {
            Text("Continue")
        }
    }
}

@Composable
fun LockJournalPage(viewModel: WelcomeViewModel) {
    var enableLock by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text("Secure Your Journal")
        Switch(
            checked = enableLock,
            onCheckedChange = { enableLock = it }
        )
        Button(onClick = { viewModel.enableLock() }) {
            Text("Continue")
        }
    }
}

class WelcomeViewModel : ViewModel() {
    fun enableNotifications() {
        // Logic to enable notifications
    }

    fun enableLock() {
        // Logic to enable app locking
    }
}