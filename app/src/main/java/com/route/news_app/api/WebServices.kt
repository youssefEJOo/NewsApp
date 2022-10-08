package com.route.news_app.api

import com.route.news_app.model.ArticlesResponse
import com.route.news_app.model.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

    @GET("/v2/top-headlines/sources")
    fun getSources(@Query("apiKey") apiKey : String,
     @Query("category") category : String)
    :Call<SourcesResponse>

    @GET("/v2/everything")
    fun getArticles(@Query("apiKey") apiKey : String,
                    @Query("sources") sourceId : String):Call<ArticlesResponse>

    @GET("/v2/everything")
    fun searchArticles(@Query("apiKey") apiKey : String,
                    @Query("q") query : String?):Call<ArticlesResponse>
}