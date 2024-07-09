package com.example.journalaibuddy.repository

import androidx.lifecycle.LiveData
import com.example.journalaibuddy.dao.JournalEntryDao
import com.example.journalaibuddy.model.JournalEntry

class JournalRepository(private val journalEntryDao: JournalEntryDao) {
    val allEntries: LiveData<List<JournalEntry>> = journalEntryDao.getAll()

    suspend fun insert(entry: JournalEntry) {
        journalEntryDao.insert(entry)
    }

    suspend fun update(entry: JournalEntry) {
        journalEntryDao.update(entry)
    }

    suspend fun delete(entry: JournalEntry) {
        journalEntryDao.delete(entry)
    }
}