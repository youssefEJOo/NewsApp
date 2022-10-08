package com.route.news_app.api

import com.route.news_app.Constance
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {
    companion object{
        private var retrofit: Retrofit? = null
        private fun getInstance():Retrofit{
            if (retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(Constance.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            }
            return retrofit!!
        }
        fun  getApis():WebServices{
            return getInstance().create(WebServices::class.java)
        }
    }
}