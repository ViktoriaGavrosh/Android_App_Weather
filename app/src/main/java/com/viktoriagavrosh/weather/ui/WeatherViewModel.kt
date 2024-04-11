package com.viktoriagavrosh.weather.ui

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.viktoriagavrosh.weather.R
import com.viktoriagavrosh.weather.WeatherApplication
import com.viktoriagavrosh.weather.data.SettingsRepository
import com.viktoriagavrosh.weather.data.WeatherApiException
import com.viktoriagavrosh.weather.data.WeatherRepository
import com.viktoriagavrosh.weather.model.Wallpaper
import com.viktoriagavrosh.weather.model.apimodel.WeatherInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val application: WeatherApplication,
    private val weatherRepository: WeatherRepository,
    private val settingsRepository: SettingsRepository
) : ViewModel() {
    private var _uiState = MutableStateFlow(WeatherInfo())
    val uiState = _uiState.asStateFlow()

    val isMusic = settingsRepository.isMusic.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = true
    )
    val isCelsius = settingsRepository.isCelsius.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = true
    )
    val wallpaperId = settingsRepository.wallpaperId.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = R.drawable.cloudy_bg
    )

    init {
        viewModelScope.launch {
            updateWeatherInfo(settingsRepository.city.first())
        }
    }

    fun changeCity(city: String) {
        viewModelScope.launch {
            settingsRepository.saveCityPreferences(city)
        }
        updateWeatherInfo(city)
    }

    fun changeMusic(isMusic: Boolean) {
        viewModelScope.launch {
            settingsRepository.saveMusicPreferences(isMusic)
        }
    }

    fun changeCelsius(temp: String) {
        val isCelsius = temp == "℃"
        viewModelScope.launch {
            settingsRepository.saveCelsiusPreferences(isCelsius)
        }
    }

    fun changeWallpaper(title: String) {
        val imageId = Wallpaper.valueOf(title).imageId
        viewModelScope.launch {
            settingsRepository.saveWallpaperIdPreferences(imageId)
        }
    }

    private fun updateWeatherInfo(city: String) {
        viewModelScope.launch {
            if (city.isEmpty()) return@launch
            try {
                _uiState.value = weatherRepository.getWeatherInfo(city)
            } catch (e: WeatherApiException) {
                Toast.makeText(application as Context, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as WeatherApplication)
                val weatherRepository = application.container.weatherRepository
                val settingsRepository = application.container.settingsRepository
                WeatherViewModel(
                    application = application,
                    weatherRepository = weatherRepository,
                    settingsRepository = settingsRepository
                )
            }
        }
    }
}