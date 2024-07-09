package com.example.journalaibuddy.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.journalaibuddy.dao.JournalEntryDao
import com.example.journalaibuddy.dao.LocalDateConverter
import com.example.journalaibuddy.model.JournalEntry

@Database(entities = [JournalEntry::class], version = 1)
@TypeConverters(LocalDateConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun journalEntryDao(): JournalEntryDao
}