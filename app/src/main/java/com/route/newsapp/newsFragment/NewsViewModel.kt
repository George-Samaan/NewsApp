package com.route.newsapp.newsFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.route.newsapp.ViewMessage
import com.route.newsapp.api.ApiManger
import com.route.newsapp.api.model.newsResponse.Article
import com.route.newsapp.api.model.newsResponse.NewsResponse
import com.route.newsapp.api.model.sourcesResponse.Source
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class NewsViewModel : ViewModel() {
    val isLoadingVisible = MutableLiveData<Boolean>()
    val message = MutableLiveData<ViewMessage>()
    val newsLiveData = MutableLiveData<List<Article?>?>()

    fun loadNews(source: Source) {
        isLoadingVisible.value = true
        source.id?.let { sourceId ->
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val response = ApiManger.getServices().getNews(sources = sourceId)
                    newsLiveData.postValue(response.articles)
                } catch (ex: HttpException) {
                    val responseJson = ex.response()?.errorBody()?.string()
                    val errorResponse = Gson().fromJson(responseJson, NewsResponse::class.java)
                    message.postValue(
                        ViewMessage
                            (message = errorResponse.message ?: "Error")
                    )
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