package com.example.journalaibuddy.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.journalaibuddy.database.AppDatabase
import com.example.journalaibuddy.model.JournalEntry
import com.example.journalaibuddy.repository.JournalRepository
import kotlinx.coroutines.launch

class JournalViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: JournalRepository
    val allEntries: List<JournalEntry>

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
}