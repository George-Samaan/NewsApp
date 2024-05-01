package com.route.data.api.model.newsResponse

import com.route.data.api.model.sourcesResponse.SourceDto
import com.route.domain.model.Article

data class ArticleDto(

    @field:com.google.gson.annotations.SerializedName("publishedAt")
    val publishedAt: String? = null,

    @field:com.google.gson.annotations.SerializedName("author")
    val author: String? = null,

    @field:com.google.gson.annotations.SerializedName("urlToImage")
    val urlToImage: String? = null,

    @field:com.google.gson.annotations.SerializedName("description")
    val description: String? = null,

    @field:com.google.gson.annotations.SerializedName("source")
    val source: SourceDto? = null,

    @field:com.google.gson.annotations.SerializedName("title")
    val title: String? = null,

    @field:com.google.gson.annotations.SerializedName("url")
    val url: String? = null,

    @field:com.google.gson.annotations.SerializedName("content")
    val content: String? = null
) {
    fun toArticle(): Article {
        return Article(
            publishedAt = publishedAt,
            author = author,
            urlToImage = urlToImage,
            description = description,
            title = title, url = url, content = content,
            source = source?.toSource()
        )
    }
}