package com.route.data.repository.source

import com.route.data.contract.datasource.newsSource.NewsSourceOnlineDataSource
import com.route.domain.contracts.repository.newsSource.NewsSourceRepository
import com.route.domain.model.Source
import javax.inject.Inject

class SourcesRepositoryImpl @Inject constructor(private val sourcesOnlineDataSource: NewsSourceOnlineDataSource) :
    NewsSourceRepository {
    override suspend fun getSources(): List<Source>? {
        return sourcesOnlineDataSource.getNewsSources()
    }
}