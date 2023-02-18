package com.route.newsapp.di

import android.content.Context
import com.route.newsapp.network.NetworkHandler
import com.route.newsapp.network.NetworkHandlerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {

    @Provides
    fun provideNetworkHandler(@ApplicationContext context: Context):NetworkHandler{
        return NetworkHandlerImpl(context)
    }
}