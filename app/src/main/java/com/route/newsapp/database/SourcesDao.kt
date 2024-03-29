package com.route.newsapp.database

import androidx.room.*
import com.route.newsapp.model.SourcesItem
@Dao
interface SourcesDao {

    @Query("select * from SourcesItem")
    suspend fun getSources():List<SourcesItem?>?

    @Query("select * from SourcesItem where category=:category")
    suspend fun getSourcesByCategoryId(category : String):List<SourcesItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateSources(sources : List<SourcesItem?>?)
}