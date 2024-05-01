package com.route.newsapp.newsSources

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.domain.contracts.repository.newsSource.NewsSourceRepository
import com.route.domain.model.Source
import com.route.newsapp.ViewMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SourcesViewModel @Inject constructor(private val sourcesRepository: NewsSourceRepository) :
    ViewModel() {
    val isLoadingVisible = MutableLiveData<Boolean>()
    val message = MutableLiveData<ViewMessage>()
    val sourcesLiveData = MutableLiveData<List<Source?>?>()


    fun getNewsSources() {
        isLoadingVisible.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val sources = sourcesRepository.getSources()
                sourcesLiveData.postValue(sources)
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