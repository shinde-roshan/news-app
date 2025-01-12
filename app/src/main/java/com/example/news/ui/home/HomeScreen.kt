package com.example.news.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.news.R
import com.example.news.data.model.News
import com.example.news.ui.theme.NewsTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    val viewModel: HomeScreenViewModel = viewModel()
    when (viewModel.homeScreenDataState.value) {
        HomeScreenDataState.Loading -> {
            LoadingScreen(modifier = Modifier.fillMaxSize())
        }

        is HomeScreenDataState.Error -> {
            ErrorScreen(
                msg = (viewModel.homeScreenDataState.value as HomeScreenDataState.Error).msg,
                modifier = Modifier.fillMaxSize()
            )
        }

        is HomeScreenDataState.Success -> {
            val newsList = (viewModel.homeScreenDataState.value as HomeScreenDataState.Success).news
            if (newsList.isEmpty()) {
                EmptyScreen(modifier = Modifier.fillMaxSize())
            } else {
                NewsList(
                    newsList = newsList,
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
fun NewsList(
    newsList: List<News>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(newsList) { news ->
            NewsRow(
                news,
                modifier = Modifier.padding(dimensionResource(R.dimen.dp_8))
            )
        }
    }
}

@Composable
fun NewsRow(
    news: News,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Row(
            modifier = modifier
                .padding(dimensionResource(R.dimen.dp_8))
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Image(
                    painter = painterResource(R.drawable.ic_broken_img),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(dimensionResource(R.dimen.dp_72))
                        .height(dimensionResource(R.dimen.dp_72))
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = dimensionResource(R.dimen.dp_4)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = news.title ?: "",
                    style = MaterialTheme.typography.titleMedium.copy(
                        lineHeight = dimensionResource(R.dimen.dp_20).value.sp
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = news.content ?: "",
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = dimensionResource(R.dimen.dp_4))
                )
            }
        }
    }

}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(dimensionResource(R.dimen.dp_60)),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )
        Text(
            text = stringResource(R.string.loading_please_wait),
            style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(dimensionResource(R.dimen.dp_8))
        )
    }
}

@Composable
fun ErrorScreen(msg: String?, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(dimensionResource(R.dimen.dp_60)),
            painter = painterResource(R.drawable.ic_error),
            contentDescription = null
        )
        Text(
            text = if (!msg.isNullOrEmpty()) msg else stringResource(R.string.failed),
            style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(dimensionResource(R.dimen.dp_8))
        )
    }
}

@Composable
fun EmptyScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(dimensionResource(R.dimen.dp_60)),
            painter = painterResource(R.drawable.ic_empty),
            contentDescription = null
        )
        Text(
            text = stringResource(R.string.no_records),
            style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(dimensionResource(R.dimen.dp_8))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRow() {
    NewsTheme {
        HomeScreen()
    }
}