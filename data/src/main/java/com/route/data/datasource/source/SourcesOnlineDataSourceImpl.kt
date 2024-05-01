package com.route.data.datasource.source

import com.route.data.api.WebServices
import com.route.data.contract.datasource.newsSource.NewsSourceOnlineDataSource
import com.route.domain.model.Source
import javax.inject.Inject


class SourcesOnlineDataSourceImpl @Inject constructor(private val webServices: WebServices) :
    NewsSourceOnlineDataSource {
    override suspend fun getNewsSources(): List<Source>? {
        return webServices.getNewsSources().sources?.map { sourceDto ->
            sourceDto!!.toSource()
        }
    }
}