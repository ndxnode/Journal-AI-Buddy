package com.example.journalaibuddy.viewmodel

import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.journalaibuddy.NotificationReceiver
import com.example.journalaibuddy.repository.SettingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Calendar

class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    private val settingsRepository: SettingRepository = SettingRepository(application)

    private val _themeSetting = MutableStateFlow(false)
    val themeSetting: StateFlow<Boolean> = _themeSetting

    private val _notificationsEnabled = MutableStateFlow(false)
    val notificationsEnabled: StateFlow<Boolean> = _notificationsEnabled

    private val _biometricEnabled = MutableStateFlow(false)
    val biometricEnabled: StateFlow<Boolean> = _biometricEnabled


    init {
        loadSettings()
    }

    private fun loadSettings() {
        viewModelScope.launch {
            settingsRepository.getThemeSetting().collect { isDarkMode ->
                _themeSetting.value = isDarkMode
            }
            settingsRepository.getNotificationsSetting().collect { isEnabled ->
                _notificationsEnabled.value = isEnabled
            }
            settingsRepository.getBiometricSetting().collect { isEnabled ->
                _biometricEnabled.value = isEnabled
            }
        }
    }

    fun toggleTheme(isDarkMode: Boolean) = viewModelScope.launch {
        settingsRepository.updateThemeSetting(isDarkMode)
    }

    fun toggleNotifications(enabled: Boolean) = viewModelScope.launch {
        settingsRepository.toggleNotifications(enabled)
    }

    fun setReminder(time: Calendar) {
        val alarmManager = getApplication<Application>().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(getApplication(), NotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(getApplication(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, time.timeInMillis, pendingIntent)
    }

    fun cancelReminder() {
        val alarmManager = getApplication<Application>().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(getApplication(), NotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(getApplication(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        alarmManager.cancel(pendingIntent)
    }

    fun enableAppLock() {
        settingsRepository.getBiometricSetting()
    }

    fun logOut() {
        TODO("Not yet implemented")
    }

}
