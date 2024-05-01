package com.route.data.repository.source

import com.route.domain.contracts.repository.newsSource.NewsSourceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SourcesRepoModule {
    @Binds
    abstract fun bindSourcesRepo(sourcesRepositoryImpl: SourcesRepositoryImpl):
            NewsSourceRepository
}