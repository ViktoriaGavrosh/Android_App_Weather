package com.viktoriagavrosh.weather

import com.viktoriagavrosh.weather.fake.FakeDataSource
import com.viktoriagavrosh.weather.fake.FakeNetworkWeatherRepository
import com.viktoriagavrosh.weather.fake.FakeUserSettingsRepository
import com.viktoriagavrosh.weather.rule.TestDispatcherRule
import com.viktoriagavrosh.weather.ui.WeatherViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class WeatherViewModelTest {

    @get: Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun weatherViewModel_updateWeatherInfo_verifyUiStateSuccess() = runTest {
        val viewModel = WeatherViewModel(
            application = WeatherApplication(),
            weatherRepository = FakeNetworkWeatherRepository(),
            settingsRepository = FakeUserSettingsRepository()
        )
        assertEquals(
            FakeDataSource.weatherInfo,
            viewModel.uiState.value
        )
    }
}