package com.route.data.repository.news

import com.route.domain.contracts.repository.news.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class NewsRepoModule {
    @Binds
    abstract fun bindNewsRepo(newsRepositoryImpl: NewsRepositoryImpl)
            : NewsRepository
}