package com.example.journalaibuddy.database

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private val Context.dataStore by preferencesDataStore("app_preferences")

object PreferencesKeys {
    val IS_FIRST_LAUNCH = booleanPreferencesKey("is_first_launch")
}

suspend fun setFirstLaunch(context: Context, isFirstLaunch: Boolean) {
    context.dataStore.edit { preferences ->
        preferences[PreferencesKeys.IS_FIRST_LAUNCH] = isFirstLaunch
    }
}

fun isFirstLaunch(context: Context): Flow<Boolean> {
    return context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[PreferencesKeys.IS_FIRST_LAUNCH] ?: true
        }
}
