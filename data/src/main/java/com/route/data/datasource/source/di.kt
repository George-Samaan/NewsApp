package com.route.data.datasource.source

import com.route.data.api.WebServices
import com.route.data.contract.datasource.newsSource.NewsSourceOnlineDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object SourceOnlineDataSourceBinder {
    @Provides
    fun provideSourceOnlineDataSource(webServices: WebServices): NewsSourceOnlineDataSource {
        return SourcesOnlineDataSourceImpl(webServices)
    }
}