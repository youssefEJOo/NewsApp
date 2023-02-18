package com.route.newsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.route.newsapp.model.SourcesItem

@Database(entities = [SourcesItem::class] , version = 1 , exportSchema = false)
abstract class MyDataBase : RoomDatabase() {
    abstract fun sourcesDao():SourcesDao

    companion object{
        var myDataBase : MyDataBase? = null
        const val NAME_DATABASE = "sourcesDataBase"
        fun init(context: Context){
            if (myDataBase == null){
                myDataBase =
                    Room.databaseBuilder(context ,
                        MyDataBase::class.java , NAME_DATABASE)
                        .fallbackToDestructiveMigration()
                        .build()
            }
        }
        fun instance():MyDataBase{
            return myDataBase!!
        }
    }
}