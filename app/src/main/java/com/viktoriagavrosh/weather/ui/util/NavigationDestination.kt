package com.viktoriagavrosh.weather.ui.util

sealed class NavigationDestination {
    abstract val route: String

    data object CurrentWeatherDestination : NavigationDestination() {
        override val route = "current"
    }

    data object DetailsDestination : NavigationDestination() {
        override val route = "details"
        const val itemIdArg = "itemId"
        val routeWithArgs = "$route/{$itemIdArg}"
    }

    data object ForecastDestination : NavigationDestination() {
        override val route = "forecast"
        const val itemIdArg = "itemId"
        val routeWithArgs = "$route/{$itemIdArg}"
    }

    data object SettingsDestination : NavigationDestination() {
        override val route = "settings"
    }
}

