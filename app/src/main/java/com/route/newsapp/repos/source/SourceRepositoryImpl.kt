package com.route.newsapp.repos.source

import com.route.newsapp.network.NetworkHandler
import com.route.newsapp.model.SourcesItem

class SourceRepositoryImpl(val onlineDataSource: SourceOnlineDataSource,
                           val offlineDataSource: SourceOfflineDataSource,
                           val networkHandler: NetworkHandler
) : SourcesRepository {
    override suspend fun getSource(category: String): List<SourcesItem?>? {
        try {
            if (networkHandler.isOnline()){
                val result = onlineDataSource.getSource(category)
                offlineDataSource.updateSources(result)
                return result

            }
            return offlineDataSource.getSourceByCategoryId(category)
        }catch (ex:Exception){
            return offlineDataSource.getSourceByCategoryId(category)
        }
    }
}