package com.viktoriagavrosh.weather.data

import com.viktoriagavrosh.weather.model.apimodel.WeatherInfo
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = ""

/**
 * Service for work with API
 */
interface WeatherApiService {
    @GET("v1/forecast.json?")
    suspend fun getWeatherInfo(
        @Query("key") key: String = API_KEY,
        @Query("q") city: String,
        @Query("days") days: Int = 3,
        @Query("aqi") aqi: String = "no",
        @Query("alerts") alerts: String = "no"
    ): WeatherInfo
}
