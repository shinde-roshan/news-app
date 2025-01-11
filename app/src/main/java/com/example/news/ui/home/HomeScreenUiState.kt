package com.example.news.ui.home

import com.example.news.data.model.News

sealed interface HomeScreenUiState {
    data class Success(val news: List<News>): HomeScreenUiState
    data class Error(val msg: String): HomeScreenUiState
    data object Loading: HomeScreenUiState
}