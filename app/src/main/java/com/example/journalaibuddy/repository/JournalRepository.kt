package com.example.journalaibuddy.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.journalaibuddy.dao.JournalEntryDao
import com.example.journalaibuddy.model.JournalEntry
import java.time.LocalDate

class JournalRepository(private val journalEntryDao: JournalEntryDao) {
    val allEntries: LiveData<List<JournalEntry>> = journalEntryDao.getAll()

    suspend fun insert(entry: JournalEntry) {
        Log.d("JournalRepository", "Inserting entry: $entry")
        journalEntryDao.insert(entry)
    }

    suspend fun update(entry: JournalEntry) {
        Log.d("JournalRepository", "Updating entry: $entry")
        journalEntryDao.update(entry)
    }

    suspend fun delete(entry: JournalEntry) {
        Log.d("JournalRepository", "Deleting entry: $entry")
        journalEntryDao.delete(entry)
    }

    suspend fun getEntryForDate(date: LocalDate?): JournalEntry? {
        return journalEntryDao.findEntryByDate(date)
    }
}