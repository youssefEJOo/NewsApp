package com.route.news_app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.route.news_app.ArticleAdapter
import com.route.news_app.Constance
import com.route.news_app.R
import com.route.news_app.api.ApiManager
import com.route.news_app.model.ArticlesResponse
import com.route.news_app.model.CategoriesModel
import com.route.news_app.model.SourcesItem
import com.route.news_app.model.SourcesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewsFragment : Fragment() {
    companion object{
        fun getInstance(category: CategoriesModel):NewsFragment{
            val newsFragment = NewsFragment()
            newsFragment.selectedCategory = category
            return newsFragment
        }
    }
    lateinit var selectedCategory: CategoriesModel
    lateinit var tabLayout : TabLayout
    var sourcesList: List<SourcesItem?>? = null
    lateinit var progressBar : ProgressBar
    var adapterArticle: ArticleAdapter = ArticleAdapter()
    lateinit var recyclerViewArticle: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        getSources()
    }
    private fun initViews() {
        tabLayout = requireView().findViewById(R.id.tab_layout)
        progressBar = requireView().findViewById(R.id.progress_bar)
        recyclerViewArticle = requireView().findViewById(R.id.articles_recycler_view)
        recyclerViewArticle.adapter = adapterArticle
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                //val source = sourcesList.get(tab!!.position)
                val source: SourcesItem = tab!!.tag as SourcesItem
                loadArticles(source!!.id!!)
                recyclerViewArticle.layoutManager!!.scrollToPosition(0)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val source: SourcesItem = tab!!.tag as SourcesItem
                loadArticles(source!!.id!!)
                recyclerViewArticle.layoutManager!!.scrollToPosition(0)
            }


        })
    }

    private fun loadArticles(sourcesId: String) {
        progressBar.isVisible = true
        ApiManager.getApis().
        getArticles(apiKey = Constance.API_KEY, sourcesId).enqueue(object :
            Callback<ArticlesResponse> {
            override fun onResponse(
                call: Call<ArticlesResponse>,
                response: Response<ArticlesResponse>
            ) {
                progressBar.isVisible = false
                adapterArticle.items = response.body()!!.articles
                adapterArticle.notifyDataSetChanged()

            }

            override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                progressBar.isVisible = true
                Toast.makeText(context , "Failed Loading a News" , Toast.LENGTH_LONG).show()
            }


        })


    }

    private fun getSources() {
        ApiManager.getApis().getSources(Constance.API_KEY, category = selectedCategory.id).enqueue(object :
            Callback<SourcesResponse> {
            override fun onResponse(
                call: Call<SourcesResponse>,
                response: Response<SourcesResponse>
            ) {
                progressBar.isVisible = false
                sourcesList = response.body()!!.sources!!
                showTabs()
            }

            override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                progressBar.isVisible = false
                Toast.makeText(context , "Failed Loading a Sources" , Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun showTabs() {
        for (i in 0 until  sourcesList!!.size){
            val tab = tabLayout.newTab()
            tab.text = sourcesList!!.get(i)!!.name
            tab.tag = sourcesList!!.get(i)
            tabLayout.addTab(tab)
        }

    }

}