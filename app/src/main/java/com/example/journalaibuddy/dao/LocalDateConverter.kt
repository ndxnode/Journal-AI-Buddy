package com.example.journalaibuddy.dao

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.ZoneId

class LocalDateConverter {
    @TypeConverter
    fun fromLocalDate(value: LocalDate): Long {
        return value.atStartOfDay(ZoneId.systemDefault()).toEpochSecond()
    }

    @TypeConverter
    fun toLocalDate(value: Long): LocalDate {
        return LocalDate.ofEpochDay(value)
    }
}
