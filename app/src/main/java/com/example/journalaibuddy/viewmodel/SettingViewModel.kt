package com.example.journalaibuddy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.journalaibuddy.repository.SettingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingViewModel(private val settingsRepository: SettingRepository) : ViewModel() {

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

    fun enableBiometric(enabled: Boolean) = viewModelScope.launch {
        // Placeholder for biometric enabling logic
    }
}