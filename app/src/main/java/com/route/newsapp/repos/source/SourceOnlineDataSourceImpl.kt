package com.route.newsapp.repos.source

import com.route.newsapp.api.Constance
import com.route.newsapp.api.WebServices
import com.route.newsapp.model.SourcesItem

class SourceOnlineDataSourceImpl(val webServices: WebServices) : SourceOnlineDataSource {
    override suspend fun getSource(category: String): List<SourcesItem?>? {
        try {
            val result =  webServices.getSources(Constance.API_KEY , category)
            return result.sources
        }catch (ex: Exception){
            throw ex
        }

    }
}