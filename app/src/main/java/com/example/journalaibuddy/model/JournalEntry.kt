package com.example.journalaibuddy.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import androidx.room.TypeConverters
import com.example.journalaibuddy.dao.LocalDateConverter

@Entity(tableName = "journal_entries")
data class JournalEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val content: String,
    @TypeConverters(LocalDateConverter::class)
    val date: LocalDate,
    val isBookmarked: Boolean,
    val imagePath: String?,
    val audioPath: String? //Ensure you handle permissions for reading and writing to storage correctly.
)