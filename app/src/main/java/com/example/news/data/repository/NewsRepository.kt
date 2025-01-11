package com.example.news.data.repository

import com.example.news.data.model.News
import com.example.news.network.NewsApi

class NewsRepository {
    suspend fun getSampleNews(category: String): List<News> {
        return NewsApi.client.getNewsHeadlines(category).articles
    }
}