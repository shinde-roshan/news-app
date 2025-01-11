package com.example.news.data.model

import com.squareup.moshi.Json

data class News(
    val source: NewsSource?,
    val title: String?,
    val author: String?,
    val description: String?,
    val content: String?,
    val url: String?,
    @Json(name = "urlToImage") val headerImgUrl: String?,
    val publishedAt: String?
)
