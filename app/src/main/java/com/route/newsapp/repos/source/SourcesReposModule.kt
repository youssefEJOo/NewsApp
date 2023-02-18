package com.route.newsapp.repos.source

import com.route.newsapp.network.NetworkHandler
import com.route.newsapp.api.WebServices
import com.route.newsapp.database.MyDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SourcesReposModule {

    @Provides
    fun provideSourcesRepos(onlineDataSource: SourceOnlineDataSource
                            , offlineDataSource: SourceOfflineDataSource
                            , networkHandler: NetworkHandler
    ):SourcesRepository{
        return SourceRepositoryImpl(onlineDataSource , offlineDataSource , networkHandler)
    }

    @Provides
    fun provideOnlineDataSource(webServices: WebServices):SourceOnlineDataSource{
        return SourceOnlineDataSourceImpl(webServices) // this fun depends on webServices
    }

    @Provides
    fun provideOfflineDataSource(myDataBase: MyDataBase):SourceOfflineDataSource{
        return SourceOfflineDataSourceImpl(myDataBase) // this fun depends on myDataBase
    }

    @Provides
    fun provideMyDataBase():MyDataBase{
        return MyDataBase.instance()
    }
}