package com.viktoriagavrosh.weather.api

import com.viktoriagavrosh.weather.data.NetworkWeatherRepository
import com.viktoriagavrosh.weather.fake.FakeDataSource
import com.viktoriagavrosh.weather.fake.FakeWeatherApiService
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkWeatherRepositoryTest {

    @Test
    fun networkWeatherRepository_getWeatherInfo_verifyWeatherInfo() = runTest {
        val repository = NetworkWeatherRepository(
            weatherApiService = FakeWeatherApiService()
        )
        assertEquals(FakeDataSource.weatherInfo, repository.getWeatherInfo(FakeDataSource.fakeCity))
    }
}