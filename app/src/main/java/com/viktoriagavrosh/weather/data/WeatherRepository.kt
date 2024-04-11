package com.viktoriagavrosh.weather.data

import com.viktoriagavrosh.weather.model.apimodel.WeatherInfo
import retrofit2.HttpException
import java.io.IOException

/**
 * Repository that gets [WeatherInfo] from a given data source
 */
interface WeatherRepository {
    suspend fun getWeatherInfo(city: String): WeatherInfo
}

/**
 * [WeatherRepository] implementation that provides functions for working with API
 */
class NetworkWeatherRepository(
    private val weatherApiService: WeatherApiService
) : WeatherRepository {
    override suspend fun getWeatherInfo(city: String): WeatherInfo {
        return try {
            weatherApiService.getWeatherInfo(city = city)
        } catch (e: IOException) {
            throw WeatherApiException("No internet")
        } catch (e: HttpException) {
            throw WeatherApiException("Wrong city name")
        }
    }
}

class WeatherApiException(override val message: String?) : Exception()