package com.route.newsapp

import android.app.Application
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.route.newsapp.api.Constance
import com.route.newsapp.database.MyDataBase
import com.route.newsapp.network.NetworkHandler
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MyDataBase.init(this)

    }

}