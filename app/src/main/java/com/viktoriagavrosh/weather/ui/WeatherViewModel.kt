package com.viktoriagavrosh.weather.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.viktoriagavrosh.weather.WeatherApplication
import com.viktoriagavrosh.weather.data.WeatherRepository
import com.viktoriagavrosh.weather.model.Wallpaper
import com.viktoriagavrosh.weather.model.apimodel.CurrentWeather
import com.viktoriagavrosh.weather.model.apimodel.Day
import com.viktoriagavrosh.weather.model.apimodel.Weather
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

    init {
        getWeatherInfo(city = "Minsk")
    }

    fun changeCity(city: String) {
        getWeatherInfo(city)
    }

    private fun getWeatherInfo(city: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    weatherInfo = weatherRepository.getWeatherInfo(city)
                )
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

    fun selectWeather(weatherDate: String) {
        val weather = if (weatherDate.isEmpty()) {
            _uiState.value.weatherInfo.currentWeather
        } else {
            _uiState.value.weatherInfo.forecast.days.first {
                it.date == weatherDate
            }.dayWeather
        }
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    selectedWeather = weather
                )
            }
        }
    }

    fun selectDay(date: String) {
        val day = _uiState.value.weatherInfo.forecast.days.first {
            it.date == date
        }
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    selectedDay = day
                )
            }
        }
    }

    fun selectDayByDate(date: String) {
        val day = _uiState.value.weatherInfo.forecast.days.first {
            it.date.substringAfter("-").replace("-", "/") == date
        }
        selectDay(day.date)
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
    val selectedWeather: Weather = CurrentWeather(),
    val selectedDay: Day = Day(),
    val settings: Settings = Settings()
)

data class Settings(
    val isMusic: Boolean = true,
    val isCelsius: Boolean = true,
    val wallpaper: Wallpaper = Wallpaper.DAY
)