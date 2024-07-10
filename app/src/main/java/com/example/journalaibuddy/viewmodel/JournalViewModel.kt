package com.example.journalaibuddy.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
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

class JournalViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: JournalRepository
    val allEntries: LiveData<List<JournalEntry>>

    private val _selectedEntry = MutableStateFlow<JournalEntry?>(null)
    val selectedEntry: StateFlow<JournalEntry?> = _selectedEntry.asStateFlow()

    init {
        val db = Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java,
            "journal_database"
        ).build()

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



}