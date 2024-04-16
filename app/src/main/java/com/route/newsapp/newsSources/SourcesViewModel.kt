package com.route.newsapp.newsSources

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.route.newsapp.ViewMessage
import com.route.newsapp.api.ApiManger
import com.route.newsapp.api.model.sourcesResponse.Source
import com.route.newsapp.api.model.sourcesResponse.SourcesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SourcesViewModel : ViewModel() {
    val isLoadingVisible = MutableLiveData<Boolean>()
    val message = MutableLiveData<ViewMessage>()
    val sourcesLiveData = MutableLiveData<List<Source?>?>()

    fun getNewsSources() {
        isLoadingVisible.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = ApiManger.getServices().getNewsSources()
                sourcesLiveData.postValue(response.sources)
            } catch (ex: HttpException) {
                val responseJson = ex.response()?.errorBody()?.string()
                val errorResponse = Gson().fromJson(
                    responseJson,
                    SourcesResponse::class.java
                )
                message.postValue(
                    ViewMessage(
                        message = errorResponse.message ?: "Something Went Wrong"
                    )
                )
            } catch (ex: Exception) {
                message.postValue(
                    ViewMessage(
                        message = ex.message ?: "Something Went Wrong",
                        posActionName = "Try Again",
                        posAction = {
                            getNewsSources()
                        }
                    )
                )
            } finally {
                isLoadingVisible.postValue(false)
            }
        }


    }
}