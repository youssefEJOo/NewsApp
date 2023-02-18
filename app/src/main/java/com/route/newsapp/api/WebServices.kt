package com.route.newsapp.api

import androidx.lifecycle.MutableLiveData
import com.route.newsapp.model.ArticleResponse
import com.route.newsapp.model.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

    @GET("/v2/top-headlines/sources")
   suspend fun getSources(@Query("apiKey") apiKey : String ,
                    @Query("category") category : String):SourcesResponse

    @GET("/v2/everything")
   suspend fun getArticles(@Query("apiKey") apiKey : String ,
                   @Query("sources") sourcesId : String):ArticleResponse

    @GET("/v2/everything")
    suspend fun searchInNews(@Query("apiKey") apiKey: String,
                             @Query("q") keyWord: String):ArticleResponse

}