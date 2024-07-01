package com.example.journalaibuddy

data class JournalEntry(
    val id: Int,
    val title: String,
    val content: String,
    val timestamp: Long
)