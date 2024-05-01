package com.route.data.contract.datasource.newsSource

import com.route.domain.model.Source

interface NewsSourceOnlineDataSource {
    suspend fun getNewsSources(): List<Source>?
}