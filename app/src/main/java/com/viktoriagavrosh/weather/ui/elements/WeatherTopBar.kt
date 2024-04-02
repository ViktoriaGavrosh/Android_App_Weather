package com.viktoriagavrosh.weather.ui.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.viktoriagavrosh.weather.R
import com.viktoriagavrosh.weather.ui.theme.WeatherTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherTopBar(
    text: String,
    isBack: Boolean = true,
    isTitleClickable: Boolean = true,
    onCityClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = text,
                style = MaterialTheme.typography.titleLarge,
                modifier = if (isTitleClickable) {
                    Modifier.clickable {
                        onCityClick()
                    }
                } else {
                    Modifier
                }
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            titleContentColor = Color.White
        ),
        navigationIcon = {
            if (isBack) {
                IconButton(
                    onClick = { onBackClick() }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = stringResource(R.string.back),
                        modifier = Modifier
                            .padding(start = dimensionResource(id = R.dimen.padding_small))
                            .size(dimensionResource(id = R.dimen.top_bar_icon_size))
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun WeatherTopBarPreview() {
    WeatherTheme {
        WeatherTopBar(
            text = "City",
            onCityClick = {}
        )
    }
}