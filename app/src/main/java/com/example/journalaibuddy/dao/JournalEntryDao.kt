package com.example.journalaibuddy.dao

import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.journalaibuddy.model.JournalEntry

interface JournalEntryDao {
    @Query("SELECT * FROM journal_entries")
    fun getAll(): List<JournalEntry>

    @Query("SELECT * FROM journal_entries WHERE id = :id")
    fun getById(id: Int): JournalEntry

    @Insert
    fun insert(entry: JournalEntry)

    @Update
    fun update(entry: JournalEntry)

    @Query("DELETE FROM journal_entries WHERE id = :id")
    fun deleteById(id: Int)

}