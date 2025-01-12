package com.example.news.data.repository

import com.example.news.data.model.News
import com.example.news.network.NewsApi

class NewsRepository {
    suspend fun getSampleNews(category: String): List<News> {
        val res = NewsApi.client.getNewsHeadlines(category).articles
        res.map { news ->
            val index = news.title?.lastIndexOf(" - ")
            if (index != null && index != -1) {
                news.title = news.title?.substring(0, index)
            }
        }
        return res
    }
}