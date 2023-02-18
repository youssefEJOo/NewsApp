package com.route.newsapp.repos.news

import android.util.Log
import com.route.newsapp.model.ArticlesItem

class NewsRepositoryImpl(val newsOnlineDataSource: NewsOnlineDataSource,
                         ) : NewsRepository {
    override suspend fun getNews(sourceId: String): List<ArticlesItem?>? {
        try {
                val result =  newsOnlineDataSource.getNewsBySourceId(sourceId)
                Log.e("getNews" , "${result.toString()}")
                return result
        }catch (ex : Exception){
            throw ex
        }

    }
}