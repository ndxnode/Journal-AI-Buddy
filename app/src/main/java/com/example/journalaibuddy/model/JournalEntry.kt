package com.example.journalaibuddy.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "journal_entries")
data class JournalEntry(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val content: String,
    val timestamp: Long

    // was thinking adding a daily habits list
    // or that can be another entity and can be linked by timestamp
    // so journal entries are once per day
    // with a section for thoughts
)