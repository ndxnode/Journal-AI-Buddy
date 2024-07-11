package com.example.journalaibuddy.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.journalaibuddy.model.JournalEntry
import java.time.LocalDate

@Dao
interface JournalEntryDao {
    @Query("SELECT * FROM journal_entries ORDER BY date DESC")
    fun getAll(): LiveData<List<JournalEntry>>

    @Query("SELECT * FROM journal_entries WHERE id = :id")
    fun getById(id: Int): LiveData<JournalEntry>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entry: JournalEntry): Long

    @Update
    fun update(entry: JournalEntry): Int

    @Query("DELETE FROM journal_entries WHERE id = :id")
    fun deleteById(id: Int)

    @Delete
    fun delete(entry: JournalEntry): Int

    @Query("SELECT * FROM journal_entries WHERE date = :date")
    suspend fun findEntryByDate(date: LocalDate?): JournalEntry?

}