package com.route.newsapp.ui.news


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.newsapp.api.Constance
import com.route.newsapp.database.MyDataBase
import com.route.newsapp.model.*
import com.route.newsapp.repos.news.*
import com.route.newsapp.repos.source.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(val newsRepository : NewsRepository,
                                        val sourcesRepository: SourcesRepository): ViewModel() {

     var progressBarLiveData = MutableLiveData<Boolean>()
     var messageLiveData  = MutableLiveData<String>()
     var sourcesResponse = MutableLiveData<List<SourcesItem?>?>()
     var articleLiveData = MutableLiveData<List<ArticlesItem?>?>()




     fun getSources(categoryId : DataCategory){
       viewModelScope.launch {
           progressBarLiveData.value = true
           try {
               val sources =  sourcesRepository.getSource(categoryId.id)
               sourcesResponse.value = sources
               progressBarLiveData.value = false
           }catch (e: Exception){
               progressBarLiveData.value = false
               messageLiveData.value = e.message
           }
       }
    }

     fun loadArticles(sourceId  : String){
        viewModelScope.launch {
            progressBarLiveData.value = true
            try {
                val articleSource = newsRepository.getNews(sourceId)
                Log.e("viewModel" , "$articleSource")
                progressBarLiveData.value = false
                articleLiveData.value = articleSource
            }catch (e: Exception){
                progressBarLiveData.value = false
                messageLiveData.value = e.message
            }

        }
    }


    }

