package com.example.news.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.news.data.repository.NewsRepository
import com.example.news.network.NewsApi.newsCategories
import kotlinx.coroutines.launch
import java.io.IOException

class HomeScreenViewModel(private val newsRepository: NewsRepository = NewsRepository()) :
    ViewModel() {
    private var _homeScreenDataState: MutableState<HomeScreenDataState> =
        mutableStateOf(HomeScreenDataState.Loading)
    val homeScreenDataState: MutableState<HomeScreenDataState> = _homeScreenDataState

    private var _homeScreenUiState: MutableState<HomeScreenUiState> =
        mutableStateOf(HomeScreenUiState(newsCategories.keys.first()))
    val homeScreenUiState: MutableState<HomeScreenUiState> = _homeScreenUiState

    init {
        getNewsHeadlines()
    }

    fun getNewsHeadlines() {
        viewModelScope.launch {
            try {
                val listResult =
                    newsRepository.getSampleNews(_homeScreenUiState.value.selectedCategory)
                _homeScreenDataState.value = HomeScreenDataState.Success(listResult)
            } catch (e: IOException) {
                _homeScreenDataState.value = HomeScreenDataState.Error(e.message.toString())
            } catch (e: HttpException) {
                _homeScreenDataState.value = HomeScreenDataState.Error(e.message.toString())
            }
        }
    }

    fun onCategorySelected(category: String) {
        _homeScreenUiState.value = _homeScreenUiState.value.copy(
            selectedCategory = category
        )
        getNewsHeadlines()
    }
}