package com.viktoriagavrosh.weather.ui.elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.viktoriagavrosh.weather.R
import com.viktoriagavrosh.weather.model.apimodel.Weather

@Composable
fun DetailButton(
    modifier: Modifier = Modifier,
    weather: Weather,
    onDetailsClick: (Weather) -> Unit,
    isWhiteBorder: Boolean = false
) {
    OutlinedButton(
        onClick = { onDetailsClick(weather) },
        modifier = modifier
            .width(dimensionResource(id = R.dimen.button_width))
            .height(dimensionResource(id = R.dimen.button_height)),
        border = BorderStroke(
            width = dimensionResource(id = R.dimen.button_border_width),
            color = if (isWhiteBorder) {
                MaterialTheme.colorScheme.onPrimary
            } else {
                MaterialTheme.colorScheme.surfaceVariant
            }
        )
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_down),
            contentDescription = stringResource(id = R.string.details),
            colorFilter = ColorFilter.tint(
                if (isWhiteBorder) {
                    MaterialTheme.colorScheme.onPrimary
                } else {
                    MaterialTheme.colorScheme.surfaceVariant
                }
            ),
            modifier = Modifier.size(dimensionResource(id = R.dimen.button_icon_size))
        )
    }
}