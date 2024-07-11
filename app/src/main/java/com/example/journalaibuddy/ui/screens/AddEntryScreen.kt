package com.example.journalaibuddy.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.journalaibuddy.model.JournalEntry
import com.example.journalaibuddy.viewmodel.JournalViewModel
import java.time.LocalDate

@Composable
fun AddEntryScreen(viewModel: JournalViewModel, navController: NavController) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()

        )
        TextField(
            value = content,
            onValueChange = { content = it },
            label = { Text("Content") },
            modifier = Modifier.height(200.dp).fillMaxWidth()
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = {
                val newEntry = JournalEntry(
                    id = 0,
                    title = title,
                    content = content,
                    date = LocalDate.now(),
                    isBookmarked = false
                )
                Log.d("AddEntryScreen", "Saving entry: $newEntry")
                viewModel.insert(newEntry)
                navController.navigate("main")
            }) {
                Text("Save")
            }
            Button(onClick = { navController.navigate("main") }) {
                Text("Cancel")
            }
        }
    }
}
