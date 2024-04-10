package com.viktoriagavrosh.weather.ui

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.viktoriagavrosh.weather.WeatherApplication
import com.viktoriagavrosh.weather.data.WeatherApiException
import com.viktoriagavrosh.weather.data.WeatherRepository
import com.viktoriagavrosh.weather.model.Wallpaper
import com.viktoriagavrosh.weather.model.apimodel.WeatherInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    private var _uiState = MutableStateFlow(WeatherState())
    val uiState = _uiState.asStateFlow()

    fun getWeatherInfo(city: String, context: Context) {
        viewModelScope.launch {
            try {
                _uiState.update {
                    it.copy(
                        weatherInfo = weatherRepository.getWeatherInfo(city)
                    )
                }
            } catch (e: WeatherApiException) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun changeMusic() {
        viewModelScope.launch {
            val settings = _uiState.value.settings
            _uiState.update {
                it.copy(
                    settings = settings.copy(
                        isMusic = !settings.isMusic
                    )
                )
            }
        }
    }

    fun changeCelsius(temp: String) {
        val isCelsius = temp == "℃"
        val settings = _uiState.value.settings
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    settings = settings.copy(
                        isCelsius = isCelsius
                    )
                )
            }
        }
    }

    fun changeWallpaper(title: String) {
        viewModelScope.launch {
            val settings = _uiState.value.settings
            _uiState.update {
                it.copy(
                    settings = settings.copy(
                        wallpaper = Wallpaper.valueOf(title)
                    )
                )
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as WeatherApplication)
                val weatherRepository = application.container.weatherRepository
                WeatherViewModel(weatherRepository = weatherRepository)
            }
        }
    }
}

data class WeatherState(
    val weatherInfo: WeatherInfo = WeatherInfo(),
    val settings: Settings = Settings()
)

data class Settings(
    val isMusic: Boolean = true,
    val isCelsius: Boolean = true,
    val wallpaper: Wallpaper = Wallpaper.DAY
)