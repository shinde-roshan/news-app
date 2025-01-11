package com.example.news.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://saurav.tech/NewsAPI/"
val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }).build())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface NewsApiService {
    @GET("top-headlines/category/{category}/in.json")
    suspend fun getNewsHeadlines(@Path("category") category: String): NewsApiResponse
}

object NewsApi {
    val client: NewsApiService by lazy {
        retrofit.create(NewsApiService::class.java)
    }
}