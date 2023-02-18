package com.route.newsapp.repos.news

import android.util.Log
import com.route.newsapp.api.Constance
import com.route.newsapp.api.WebServices
import com.route.newsapp.model.ArticlesItem

class NewsOnlineDataSourceImpl(val webService : WebServices) : NewsOnlineDataSource {
    override suspend fun getNewsBySourceId(sourceId: String): List<ArticlesItem?>? {
        try{
            val result = webService.getArticles(Constance.API_KEY , sourceId)
            Log.e("NewsOnlineDataSourceImpl" , "${result.toString()}")
            return result.articles
        }catch (ex : Exception){
            throw ex
        }

    }
}