package com.route.data.datasource.news

import com.route.data.api.WebServices
import com.route.data.contract.datasource.news.NewsOnlineDataSource
import com.route.domain.model.Article
import javax.inject.Inject


class NewsOnlineDataSourceImpl @Inject constructor(private val webServices: WebServices) :
    NewsOnlineDataSource {
    override suspend fun getNews(sourceId: String): List<Article>? {
        return webServices.getNews(sources = sourceId).articles?.map { articleDto ->
            articleDto!!.toArticle()
        }
    }
}