package com.viktoriagavrosh.weather

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.viktoriagavrosh.weather.ui.WeatherApp
import com.viktoriagavrosh.weather.ui.WeatherViewModel
import com.viktoriagavrosh.weather.ui.theme.WeatherTheme

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: WeatherViewModel
    private lateinit var isMusic: State<Boolean>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            viewModel = viewModel(factory = WeatherViewModel.Factory)
            isMusic = viewModel.isMusic.collectAsState()

            if (isMusic.value) {
                startService(Intent(this@MainActivity, MusicService::class.java))
            } else {
                stopService(Intent(this@MainActivity, MusicService::class.java))
            }
            WeatherTheme {
                WeatherApp(
                    viewModel = viewModel
                )
            }
        }
    }

    override fun onStop() {
        super.onStop()
        stopService(Intent(this@MainActivity, MusicService::class.java))
    }

    override fun onRestart() {
        super.onRestart()
        if (isMusic.value) {
            startService(Intent(this@MainActivity, MusicService::class.java))
        }
    }
}
