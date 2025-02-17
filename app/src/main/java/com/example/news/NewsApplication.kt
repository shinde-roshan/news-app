package com.example.news

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.news.data.repository.UserPreferenceRepository

private const val NEWS_APP_PREFERENCES_NAME = "news_app_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = NEWS_APP_PREFERENCES_NAME
)

class NewsApplication: Application() {
    lateinit var userPreferenceRepository: UserPreferenceRepository

    override fun onCreate() {
        super.onCreate()
        userPreferenceRepository = UserPreferenceRepository(dataStore = dataStore)
    }
}