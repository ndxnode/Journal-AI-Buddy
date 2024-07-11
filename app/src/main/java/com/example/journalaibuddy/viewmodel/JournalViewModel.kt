package com.example.journalaibuddy.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.journalaibuddy.database.AppDatabase
import com.example.journalaibuddy.model.JournalEntry
import com.example.journalaibuddy.repository.JournalRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class JournalViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: JournalRepository
    val allEntries: LiveData<List<JournalEntry>>

    private val _filteredEntries = MutableLiveData<List<JournalEntry>>()
    val filteredEntries: LiveData<List<JournalEntry>> get() = _filteredEntries

    private val _selectedEntry = MutableStateFlow<JournalEntry?>(null)
    val selectedEntry: StateFlow<JournalEntry?> = _selectedEntry.asStateFlow()

    init {
        val db = AppDatabase.getDatabase(application)
        val journalEntryDao = db.journalEntryDao()
        repository = JournalRepository(journalEntryDao)
        allEntries = repository.allEntries
    }

    fun insert(entry: JournalEntry) = viewModelScope.launch {
        repository.insert(entry)
    }

    fun update(entry: JournalEntry) = viewModelScope.launch {
        repository.update(entry)
    }

    fun delete(entry: JournalEntry) = viewModelScope.launch {
        repository.delete(entry)
    }
    fun toggleBookmark(entry: JournalEntry) = viewModelScope.launch {
        val updatedEntry = entry.copy(isBookmarked = !entry.isBookmarked)
        repository.update(updatedEntry)
    }

    fun editEntry(entry: JournalEntry) = update(entry)


    fun getEntryForDate(date: LocalDate) {
        viewModelScope.launch {
            val entry = repository.getEntryForDate(date)
            _selectedEntry.emit(entry)
        }
    }

    fun filterEntries(filter: String) {
        val entries = allEntries.value ?: return
        val filteredList = when (filter) {
            "This Week" -> entries.filter { it.date.isAfter(LocalDate.now().minus(7, ChronoUnit.DAYS)) }
            "Last Week" -> entries.filter {
                val lastWeekStart = LocalDate.now().minus(14, ChronoUnit.DAYS)
                val lastWeekEnd = LocalDate.now().minus(7, ChronoUnit.DAYS)
                it.date.isAfter(lastWeekStart) && it.date.isBefore(lastWeekEnd)
            }
            "Bookmarked" -> entries.filter { it.isBookmarked }
            else -> entries
        }
        _filteredEntries.value = filteredList
    }


}