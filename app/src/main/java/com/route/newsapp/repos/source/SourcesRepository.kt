package com.route.newsapp.repos.source

import com.route.newsapp.model.SourcesItem

interface SourcesRepository {

    suspend fun getSource(category : String): List<SourcesItem?>?
}

interface SourceOnlineDataSource{

    suspend fun getSource(category: String): List<SourcesItem?>?
}

interface SourceOfflineDataSource{

    suspend fun updateSources(sources: List<SourcesItem?>?)
    suspend fun getSourceByCategoryId(category: String): List<SourcesItem>
}