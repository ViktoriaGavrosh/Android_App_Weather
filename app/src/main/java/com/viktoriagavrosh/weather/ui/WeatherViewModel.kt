package com.viktoriagavrosh.weather.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.viktoriagavrosh.weather.WeatherApplication
import com.viktoriagavrosh.weather.data.WeatherRepository
import com.viktoriagavrosh.weather.model.apimodel.WeatherInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    private var _uiState = MutableStateFlow(WeatherInfo())
    val uiState = _uiState.asStateFlow()

    init {
        getWeatherInfo(city = "Minsk")
    }

    fun changeCity(city: String) {
        getWeatherInfo(city)
    }

    private fun getWeatherInfo(city: String) {
        viewModelScope.launch {
            _uiState.value = weatherRepository.getWeatherInfo(city)
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