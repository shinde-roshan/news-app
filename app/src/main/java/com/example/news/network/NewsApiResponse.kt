package com.example.news.network

import com.example.news.data.model.News

data class NewsApiResponse(
    val articles: List<News>
)
