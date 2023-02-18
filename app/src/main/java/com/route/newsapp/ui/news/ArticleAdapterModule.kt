package com.route.newsapp.ui.news

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ArticleAdapterModule {

    @Provides
    fun provideArticleAdapter(): ArticleAdapter{
        return ArticleAdapter()
    }
}