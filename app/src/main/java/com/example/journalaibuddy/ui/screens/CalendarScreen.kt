package com.example.journalaibuddy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.journalaibuddy.viewmodel.JournalViewModel
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.*

@Composable
fun CalendarScreen(viewModel: JournalViewModel) {
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }
    val selectedDate = remember { mutableStateOf<LocalDate?>(null) }

    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { currentMonth = currentMonth.minusMonths(1) }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Previous Month")
            }
            Text(
                "${currentMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault())} ${currentMonth.year}",
                style = MaterialTheme.typography.bodyLarge
            )
            IconButton(onClick = { currentMonth = currentMonth.plusMonths(1) }) {
                Icon(Icons.Default.ArrowForward, contentDescription = "Next Month")
            }
        }

        CalendarDayGrid(currentMonth, selectedDate, viewModel)
        selectedDate.value?.let {
            JournalEntryDetails(viewModel)
        }
    }
}

@Composable
fun CalendarDayGrid(currentMonth: YearMonth, selectedDate: MutableState<LocalDate?>, viewModel: JournalViewModel) {
    val daysInMonth = currentMonth.lengthOfMonth()
    val firstDayOfMonth = currentMonth.atDay(1).dayOfWeek.value

    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(daysInMonth) { day ->
            val date = currentMonth.atDay(day + 1)
            CalendarDay(date, selectedDate) { selectedDate ->
                viewModel.getEntryForDate(selectedDate)
            }
        }
    }
}

@Composable
fun CalendarDay(date: LocalDate, selectedDate: MutableState<LocalDate?>, showEntryDetails: (LocalDate) -> Unit) {
    val isSelected = selectedDate.value == date

    Box(
        modifier = Modifier
            .padding(4.dp)
            .aspectRatio(1f)
            .background(if (isSelected) Color.Cyan else Color.Transparent)
            .clickable {
                selectedDate.value = date
                showEntryDetails(date)
            },
        contentAlignment = Alignment.Center
    ) {
        Text(text = date.dayOfMonth.toString())
    }
}

@Composable
fun JournalEntryDetails(viewModel: JournalViewModel) {
    val entry by viewModel.selectedEntry.collectAsState()

    entry?.let { journalEntry ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.LightGray)
        ) {
            Text("Title: ${journalEntry.title}", style = MaterialTheme.typography.titleSmall)
            Text("Content: ${journalEntry.content}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
