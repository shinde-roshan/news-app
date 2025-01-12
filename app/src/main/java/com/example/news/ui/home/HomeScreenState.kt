package com.example.news.ui.home

import com.example.news.data.model.News

sealed interface HomeScreenDataState {
    data class Success(val news: List<News>) : HomeScreenDataState
    data class Error(val msg: String) : HomeScreenDataState
    data object Loading : HomeScreenDataState
}

data class HomeScreenUiState (
    var selectedCategory: String
)