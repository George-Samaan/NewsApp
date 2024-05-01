package com.route.newsapp.newsFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.domain.contracts.repository.news.NewsRepository
import com.route.domain.model.Article
import com.route.domain.model.Source
import com.route.newsapp.ViewMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {
    val isLoadingVisible = MutableLiveData<Boolean>()
    val message = MutableLiveData<ViewMessage>()
    val newsLiveData = MutableLiveData<List<Article?>?>()

    fun loadNews(source: Source) {
        isLoadingVisible.value = true
        source.id?.let { sourceId ->
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val article = newsRepository.getNews(sourceId)
                    newsLiveData.postValue(article)
                } catch (ex: Exception) {
                    message.postValue(
                        ViewMessage(
                            message = ex.message ?: "Something Went Wrong"
                        )
                    )
                } finally {
                    isLoadingVisible.postValue(false)
                }
            }
        }
    }
}