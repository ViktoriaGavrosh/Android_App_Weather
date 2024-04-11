package com.viktoriagavrosh.weather

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.viktoriagavrosh.weather.data.AppContainer
import com.viktoriagavrosh.weather.data.DefaultAppContainer

/**
 * for DataStore Preferences
 */
private const val SETTINGS_PREFERENCES_NAME = "settings_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = SETTINGS_PREFERENCES_NAME
)

class WeatherApplication : Application() {
    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(dataStore)
    }
}