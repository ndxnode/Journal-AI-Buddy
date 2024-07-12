package com.example.journalaibuddy.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.journalaibuddy.model.JournalEntry
import com.example.journalaibuddy.viewmodel.JournalViewModel
import com.google.firebase.auth.FirebaseAuth


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: JournalViewModel, navController: NavController) {
    val journalEntries by viewModel.filteredEntries.observeAsState(initial = emptyList())

    Log.d("MainScreen", "Observed entries: $journalEntries")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Welcome to your Journal") },
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("addEntry") }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Entry")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            items(journalEntries.size) { index ->
                JournalEntryCard(journalEntries[index], viewModel)
            }
        }
    }

}

@Composable
fun JournalEntryCard(entry: JournalEntry, viewModel: JournalViewModel) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = entry.title, style = MaterialTheme.typography.titleLarge)
            Text(text = entry.content, style = MaterialTheme.typography.bodyLarge)
            Text(text = entry.date.toString(), style = MaterialTheme.typography.bodySmall)

            Row {
                IconButton(onClick = { /* Handle edit entry */ }) {
                    Icon(Icons.Filled.Edit, contentDescription = "Edit")
                }
                IconButton(onClick = { viewModel.delete(entry) }) {
                    Icon(Icons.Filled.Delete, contentDescription = "Delete")
                }
                IconButton(onClick = { viewModel.toggleBookmark(entry) }) {
                    Icon(
                        imageVector = if (entry.isBookmarked) Icons.Filled.Star else Icons.Filled.Star,
                        contentDescription = "Bookmark"
                    )
                }
            }
        }
    }
}
