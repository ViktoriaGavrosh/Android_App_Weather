package com.viktoriagavrosh.weather.fake

import com.viktoriagavrosh.weather.data.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeUserSettingsRepository : SettingsRepository {
    override val city: Flow<String> = flowOf(FakeDataSource.fakeCity)
    override val isMusic: Flow<Boolean> = flowOf(FakeDataSource.isMusic)
    override val isCelsius: Flow<Boolean> = flowOf(FakeDataSource.isCelsuis)
    override val wallpaperId: Flow<Int> = flowOf(FakeDataSource.wallpaperId)

    override suspend fun saveCityPreferences(city: String) {
        TODO("Not yet implemented")
    }

    override suspend fun saveMusicPreferences(isMusic: Boolean) {
        TODO("Not yet implemented")
    }

    override suspend fun saveCelsiusPreferences(isCelsius: Boolean) {
        TODO("Not yet implemented")
    }

    override suspend fun saveWallpaperIdPreferences(wallpaperId: Int) {
        TODO("Not yet implemented")
    }
}