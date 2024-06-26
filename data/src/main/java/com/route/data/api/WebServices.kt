package com.route.data.api

import com.route.data.api.model.newsResponse.NewsResponse
import com.route.data.api.model.sourcesResponse.SourcesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

    @GET("v2/top-headlines/sources")
    suspend fun getNewsSources(
        @Query("apiKey")
        apiKey: String = Constants.apiKey
    ): SourcesResponse


    @GET("v2/everything")
    suspend fun getNews(
        @Query("apiKey") apiKey: String = Constants.apiKey,
        @Query("sources") sources: String
    ): NewsResponse

}