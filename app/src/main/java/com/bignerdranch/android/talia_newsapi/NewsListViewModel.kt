package com.bignerdranch.android.talia_newsapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import kotlinx.coroutines.Dispatchers

class NewsListViewModel(private val repository: NewsRepository) : ViewModel() {
    private val _current = MutableLiveData<String>()

    // live data to update when we change spinner
    val articles: LiveData<List<Article>> = _current.switchMap { category ->
        liveData(Dispatchers.IO) {
            val headlines = repository.getTopHeadlines("us", "677a4cbee8564fed8b3dff616cd871e7", category)
            val articlesList = if (headlines?.articles != null) {
                headlines.articles
            } else {
                emptyList()
            }
            emit(articlesList)        }
    }
    init {
        _current.value = "Technology"
    }
    fun setCategory(category: String) {
        _current.value = category
    }
}