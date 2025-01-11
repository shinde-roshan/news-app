package com.example.news.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.news.data.repository.NewsRepository
import kotlinx.coroutines.launch
import java.io.IOException

class HomeScreenViewModel(private val newsRepository: NewsRepository = NewsRepository()): ViewModel() {
    private var _homeUiState: MutableState<HomeScreenUiState> =
        mutableStateOf(HomeScreenUiState.Loading)
    val homeUiState: MutableState<HomeScreenUiState> = _homeUiState

    init {
        getNewsHeadlines()
    }

    fun getNewsHeadlines() {
        viewModelScope.launch {
            try {
                val listResult = newsRepository.getSampleNews("sports")
                _homeUiState.value = HomeScreenUiState.Success(listResult)
            } catch (e: IOException) {
                _homeUiState.value = HomeScreenUiState.Error(e.message.toString())
            } catch (e: HttpException) {
                _homeUiState.value = HomeScreenUiState.Error(e.message.toString())
            }
        }
    }
}