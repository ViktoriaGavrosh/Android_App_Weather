package com.viktoriagavrosh.weather.ui.elements

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import com.viktoriagavrosh.weather.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WeatherTabRow(
    modifier: Modifier = Modifier,
    tabList: List<String>,
    pagerState: PagerState
) {
    val tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = modifier
    ) {
        TabRow(
            selectedTabIndex = tabIndex,
            modifier = Modifier
                .clip(RoundedCornerShape(dimensionResource(id = R.dimen.corner_size))),
            contentColor = MaterialTheme.colorScheme.onPrimary
        ) {
            tabList.forEachIndexed { index, text ->
                Tab(
                    selected = false,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    modifier = Modifier.testTag(text),
                    text = {
                        Text(
                            text = text,
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                )
            }
        }
    }
}

