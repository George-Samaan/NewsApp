package com.route.data.contract.datasource.news

import com.route.domain.model.Article

interface NewsOnlineDataSource {
    suspend fun getNews(sourceId: String): List<Article>?
}