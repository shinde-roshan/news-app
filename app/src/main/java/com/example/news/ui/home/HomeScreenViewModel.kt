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
    private var _homeScreenDataState: MutableState<HomeScreenDataState> =
        mutableStateOf(HomeScreenDataState.Loading)
    val homeScreenDataState: MutableState<HomeScreenDataState> = _homeScreenDataState

    init {
        getNewsHeadlines()
    }

    fun getNewsHeadlines() {
        viewModelScope.launch {
            try {
                val listResult = newsRepository.getSampleNews("sports")
                _homeScreenDataState.value = HomeScreenDataState.Success(listResult)
            } catch (e: IOException) {
                _homeScreenDataState.value = HomeScreenDataState.Error(e.message.toString())
            } catch (e: HttpException) {
                _homeScreenDataState.value = HomeScreenDataState.Error(e.message.toString())
            }
        }
    }
}