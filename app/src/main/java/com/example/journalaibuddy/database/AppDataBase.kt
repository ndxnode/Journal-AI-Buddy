package com.example.journalaibuddy.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.journalaibuddy.dao.JournalEntryDao
import com.example.journalaibuddy.model.JournalEntry

@Database(entities = [JournalEntry::class], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun journalEntryDao(): JournalEntryDao
}