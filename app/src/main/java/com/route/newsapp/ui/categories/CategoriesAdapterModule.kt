package com.route.newsapp.ui.categories

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object CategoriesAdapterModule {

    @Provides
    fun provideCategoryAdapter(): CategoriesAdapter{
        return CategoriesAdapter()
    }
}