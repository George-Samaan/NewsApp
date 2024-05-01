package com.route.data.datasource.news

import com.route.data.contract.datasource.news.NewsOnlineDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class NewsDataSourceBinder {
    @Binds
    abstract fun bindNewsOnlineDataSource(
        newsOnlineDataSourceImpl: NewsOnlineDataSourceImpl
    ): NewsOnlineDataSource
}