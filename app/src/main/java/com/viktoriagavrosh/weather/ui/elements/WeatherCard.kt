package com.viktoriagavrosh.weather.ui.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.viktoriagavrosh.weather.R
import com.viktoriagavrosh.weather.ui.theme.WeatherTheme

@Composable
fun WeatherCard(
    condition: String,
    iconUri: String,
    temp: Double,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = condition,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_large))
            )
            AsyncImage(
                model = ImageRequest
                    .Builder(context = LocalContext.current)
                    .data(iconUri)
                    .build(),
                contentDescription = condition,
                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(id = R.drawable.ic_broken_image),
                modifier = Modifier.size(dimensionResource(id = R.dimen.image_size))
            )
            Text(
                text = "${temp.toInt()} ℃",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_large))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherCardPreview() {
    WeatherTheme {
        WeatherCard(
            condition = "Condition",
            iconUri = "",
            temp = 10.6
        )
    }
}