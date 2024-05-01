package com.route.data.repository.news

import com.route.data.contract.datasource.news.NewsOnlineDataSource
import com.route.domain.contracts.repository.news.NewsRepository
import com.route.domain.model.Article
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val newsOnlineDataSource: NewsOnlineDataSource) :
    NewsRepository {
    override suspend fun getNews(sourceId: String): List<Article>? {
        return newsOnlineDataSource.getNews(sourceId)

    }
}