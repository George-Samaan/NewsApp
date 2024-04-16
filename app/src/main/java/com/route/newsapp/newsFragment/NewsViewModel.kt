package com.route.newsapp.newsFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.route.newsapp.ViewMessage
import com.route.newsapp.api.ApiManger
import com.route.newsapp.api.model.newsResponse.Article
import com.route.newsapp.api.model.newsResponse.NewsResponse
import com.route.newsapp.api.model.sourcesResponse.Source
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {
    val isLoadingVisible = MutableLiveData<Boolean>()
    val message = MutableLiveData<ViewMessage>()
    val newsLiveData = MutableLiveData<List<Article?>?>()

    fun loadNews(source: Source) {
        isLoadingVisible.value = true
        source.id?.let { sourceId ->
            ApiManger.getServices()
                .getNews(sources = sourceId)
                .enqueue(object : Callback<NewsResponse> {
                    override fun onResponse(
                        call: Call<NewsResponse>,
                        response: Response<NewsResponse>
                    ) {
                        isLoadingVisible.value = false
                        if (response.isSuccessful) {
                            newsLiveData.value = response.body()?.articles
                            return
                        }
                        val responseJson = response.errorBody()?.string()
                        val errorResponse = Gson().fromJson(responseJson, NewsResponse::class.java)
                        message.value = ViewMessage(message = errorResponse.message ?: "Error")

                    }

                    override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                        isLoadingVisible.value = false
                        message.value = ViewMessage(
                            message = t.message ?: "Something Went Wrong"
                        )
                    }
                })
        }
    }
}