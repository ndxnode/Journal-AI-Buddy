package com.example.journalaibuddy.repository

import androidx.lifecycle.LiveData
import com.example.journalaibuddy.dao.JournalEntryDao
import com.example.journalaibuddy.model.JournalEntry
import java.time.LocalDate

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

    suspend fun getEntryForDate(date: LocalDate?): JournalEntry? {
        return journalEntryDao.findEntryByDate(date)
    }
}