package com.route.domain.contracts.repository.news

import com.route.domain.model.Article

interface NewsRepository {
    suspend fun getNews(sourceId: String): List<Article>?
}