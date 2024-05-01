package com.route.domain.contracts.repository.newsSource

import com.route.domain.model.Source

interface NewsSourceRepository {

    suspend fun getSources(): List<Source>?
}