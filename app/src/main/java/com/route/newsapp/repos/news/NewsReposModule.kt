package com.route.newsapp.repos.news

import com.route.newsapp.api.WebServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NewsReposModule {

    @Provides
    fun provideNewsRepository(newsOnlineDataSource: NewsOnlineDataSource):NewsRepository{
        return NewsRepositoryImpl(newsOnlineDataSource)
    }

    @Provides
    fun provideNewsOnlineDataSource(webServices: WebServices):NewsOnlineDataSource{
        return NewsOnlineDataSourceImpl(webServices)
    }
}