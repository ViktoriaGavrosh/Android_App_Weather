package com.viktoriagavrosh.weather.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.viktoriagavrosh.weather.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

/**
 * Repository that save and get settings from a given data source
 */
interface SettingsRepository {
    val city: Flow<String>
    val isMusic:Flow<Boolean>
    val isCelsius: Flow<Boolean>
    val wallpaperId: Flow<Int>

    suspend fun saveCityPreferences(city: String)
    suspend fun saveMusicPreferences(isMusic: Boolean)
    suspend fun saveCelsiusPreferences(isCelsius: Boolean)
    suspend fun saveWallpaperIdPreferences(wallpaperId: Int)
}

/**
 * [SettingsRepository] implementation that provides functions for working with DataStore Preferences
 */
class UserSettingsRepository(
    private val dataStore: DataStore<Preferences>
) : SettingsRepository {
    override val city: Flow<String> = dataStore.data.catch {
        if (it is IOException) emit(emptyPreferences()) else throw it
    }
        .map { preferences ->
            preferences[CITY] ?: "Minsk"
        }

    override val isMusic: Flow<Boolean> = dataStore.data.catch {
        if (it is IOException) emit(emptyPreferences()) else throw it
    }
        .map { preferences ->
            preferences[IS_MUSIC] ?: true
        }

    override val isCelsius: Flow<Boolean> = dataStore.data.catch {
        if (it is IOException) emit(emptyPreferences()) else throw it
    }
        .map { preferences ->
            preferences[IS_CELSIUS] ?: true
        }

    override val wallpaperId: Flow<Int> = dataStore.data.catch {
        if (it is IOException) emit(emptyPreferences()) else throw it
    }
        .map { preferences ->
            preferences[WALLPAPER_ID] ?: R.drawable.cloudy_bg
        }

    companion object {
        val CITY = stringPreferencesKey("city")
        val IS_MUSIC = booleanPreferencesKey("is_music")
        val IS_CELSIUS = booleanPreferencesKey("is_celsius")
        val WALLPAPER_ID = intPreferencesKey("wallpaper_id")
    }

    override suspend fun saveCityPreferences(city: String) {
        dataStore.edit { preferences ->
            preferences[CITY] = city
        }
    }


    override suspend fun saveMusicPreferences(isMusic: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_MUSIC] = isMusic
        }
    }

    override suspend fun saveCelsiusPreferences(isCelsius: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_CELSIUS] = isCelsius
        }
    }

    override suspend fun saveWallpaperIdPreferences(wallpaperId: Int) {
        dataStore.edit { preferences ->
            preferences[WALLPAPER_ID] = wallpaperId
        }
    }
}

