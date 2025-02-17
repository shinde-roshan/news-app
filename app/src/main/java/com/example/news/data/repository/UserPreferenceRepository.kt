package com.example.news.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.news.network.NewsApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class UserPreferenceRepository(
    private val dataStore: DataStore<Preferences>
) {
    private companion object {
        val NEWS_CATEGORY = stringPreferencesKey("news_category")
    }

    val newsCategory: Flow<String> = dataStore.data
        .catch { e ->
            if (e is IOException) emit(emptyPreferences())
            else throw e
        }
        .map { preferences ->
            preferences[NEWS_CATEGORY] ?: NewsApi.newsCategories.keys.first()
        }

    suspend fun setNewsCategory(category: String) {
        dataStore.edit { preferences ->
            preferences[NEWS_CATEGORY] = category
        }
    }
}