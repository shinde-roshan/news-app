package com.example.news.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.network.HttpException
import com.example.news.NewsApplication
import com.example.news.data.repository.NewsRepository
import com.example.news.data.repository.UserPreferenceRepository
import com.example.news.network.NewsApi.newsCategories
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.IOException

class HomeScreenViewModel(
    private val newsRepository: NewsRepository,
    private val userPreferenceRepository: UserPreferenceRepository
) : ViewModel() {
    private var _homeScreenDataState: MutableState<HomeScreenDataState> =
        mutableStateOf(HomeScreenDataState.Loading)
    val homeScreenDataState: MutableState<HomeScreenDataState> = _homeScreenDataState

    private var _homeScreenUiState: StateFlow<HomeScreenUiState> =
        userPreferenceRepository.newsCategory.map { newsCategory ->
            HomeScreenUiState(newsCategory)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = HomeScreenUiState(newsCategories.keys.first())
        )
    val homeScreenUiState: StateFlow<HomeScreenUiState> = _homeScreenUiState

    init {
        viewModelScope.launch {
            _homeScreenUiState.collect {
                getNewsHeadlines(it.selectedCategory)
            }
        }
    }

    private fun getNewsHeadlines(category: String) {
        viewModelScope.launch {
            try {
                val listResult =
                    newsRepository.getSampleNews(category)
                _homeScreenDataState.value = HomeScreenDataState.Success(listResult)
            } catch (e: IOException) {
                _homeScreenDataState.value = HomeScreenDataState.Error(e.message.toString())
            } catch (e: HttpException) {
                _homeScreenDataState.value = HomeScreenDataState.Error(e.message.toString())
            }
        }
    }

    fun onCategorySelected(category: String) {
        viewModelScope.launch {
            userPreferenceRepository.setNewsCategory(category)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as NewsApplication)
                HomeScreenViewModel(
                    newsRepository = application.newsRepository,
                    userPreferenceRepository = application.userPreferenceRepository
                )
            }
        }
    }
}