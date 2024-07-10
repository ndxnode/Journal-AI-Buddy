package com.example.journalaibuddy.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.journalaibuddy.model.JournalEntry
import com.example.journalaibuddy.viewmodel.JournalViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun MainScreen(viewModel: JournalViewModel) {
    val journalEntries by viewModel.allEntries.observeAsState(initial = emptyList()) // convert livedata from viewmodel so compose func can use it
    val navController = rememberNavController() // navigation controller


    Scaffold(
        topBar = { TopAppBar(title = { Text("Journal AI Buddy") }) },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = { /* Navigate to add entry screen */ }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Entry")
            }
        }
    ) {

        LazyColumn {
            items(journalEntries.size) { index ->
                JournalEntryCard(journalEntries[index], viewModel)
            }
        }
    }

}

@Composable
fun JournalEntryCard(entry: JournalEntry, viewModel: JournalViewModel) {
    Card(modifier = Modifier.padding(8.dp).fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = entry.title, style = MaterialTheme.typography.titleLarge)
            Text(text = entry.content, style = MaterialTheme.typography.bodyLarge)
            Text(text = entry.date.toString(), style = MaterialTheme.typography.bodySmall)

            Row {
                IconButton(onClick = { viewModel.editEntry(entry) }) {
                    Icon(Icons.Filled.Edit, contentDescription = "Edit")
                }
                IconButton(onClick = { viewModel.delete(entry) }) {
                    Icon(Icons.Filled.Delete, contentDescription = "Delete")
                }
                IconButton(onClick = { viewModel.toggleBookmark(entry) }) {
                    Icon(if (entry.isBookmarked) Icons.Filled.Star else Icons.Filled.Star, contentDescription = "Bookmark")
                }
            }

        }
    }
}



