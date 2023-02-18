package com.route.newsapp.repos.source

import com.route.newsapp.database.MyDataBase
import com.route.newsapp.model.SourcesItem

class SourceOfflineDataSourceImpl(val myDataBase: MyDataBase) : SourceOfflineDataSource {
    override suspend fun updateSources(sources: List<SourcesItem?>?) {
        myDataBase.sourcesDao().updateSources(sources)
    }

    override suspend fun getSourceByCategoryId(category: String): List<SourcesItem> {
        val result = myDataBase.sourcesDao().getSourcesByCategoryId(category)
        return result
    }
}