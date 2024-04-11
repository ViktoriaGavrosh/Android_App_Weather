package com.viktoriagavrosh.weather.model

import androidx.annotation.DrawableRes
import com.viktoriagavrosh.weather.R

/**
 * Enum class contains kinds of wallpaper for app
 */
enum class Wallpaper(@DrawableRes val imageId: Int) {
    CLOUDY(R.drawable.cloudy_bg),
    SKY(R.drawable.sky_bg),
    SUNNY(R.drawable.sunny_bg)
}