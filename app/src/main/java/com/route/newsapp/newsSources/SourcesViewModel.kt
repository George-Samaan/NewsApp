package com.route.newsapp.newsSources

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.route.newsapp.ViewMessage
import com.route.newsapp.api.ApiManger
import com.route.newsapp.api.model.sourcesResponse.Source
import com.route.newsapp.api.model.sourcesResponse.SourcesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SourcesViewModel : ViewModel() {
    val isLoadingVisible = MutableLiveData<Boolean>()
    val message = MutableLiveData<ViewMessage>()
    val sourcesLiveData = MutableLiveData<List<Source?>?>()

    fun getNewsSources() {
        isLoadingVisible.value = true
        ApiManger.getServices().getNewsSources()
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    isLoadingVisible.value = false
                    if (response.isSuccessful) {
                        sourcesLiveData.value = response.body()?.sources
                        return
                    }
                    val responseJson = response.errorBody()?.string()
                    val errorResponse = Gson().fromJson(
                        responseJson,
                        SourcesResponse::class.java
                    )
                    message.value = ViewMessage(
                        message = errorResponse.message ?: "Something Went Wrong"
                    )
                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    isLoadingVisible.value = false
                    message.value = ViewMessage(
                        message = t.message ?: "Something Went Wrong",
                        posActionName = "Try Again",
                        posAction = {
                            getNewsSources()
                        },
                    )
                }
            })
    }
}