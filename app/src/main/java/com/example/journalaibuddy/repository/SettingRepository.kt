package com.example.journalaibuddy.repository

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "settings")

class SettingRepository(private val context: Context) {

    fun getThemeSetting(): Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[PreferencesKeys.THEME_KEY] ?: false
    }

    fun getNotificationsSetting(): Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[PreferencesKeys.NOTIFICATIONS_KEY] ?: false
    }

    fun getBiometricSetting(): Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[PreferencesKeys.BIOMETRIC_KEY] ?: false
    }
    fun logOut() {
        // Logic for logging out

    }
    suspend fun updateThemeSetting(isDark: Boolean) {
        context.dataStore.edit { settings ->
            settings[PreferencesKeys.THEME_KEY] = isDark
        }
    }

    fun toggleNotifications(enabled: Boolean) {

    }

    object PreferencesKeys {
        val THEME_KEY = booleanPreferencesKey("theme")
        val NOTIFICATIONS_KEY = booleanPreferencesKey("notifications")
        val BIOMETRIC_KEY = booleanPreferencesKey("biometric")
    }
}