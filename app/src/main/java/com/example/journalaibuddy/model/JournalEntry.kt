package com.example.journalaibuddy.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "journal_entries")
data class JournalEntry(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val content: String,
    val timestamp: Long
)