package com.route.newsapp.ui.news

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.route.newsapp.R
import com.route.newsapp.databinding.FragmentNewsBinding
import com.route.newsapp.model.ArticlesItem
import com.route.newsapp.model.DataCategory
import com.route.newsapp.model.SourcesItem
import com.route.newsapp.ui.news_details_activity.NewsDetailsActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment : Fragment() {

    companion object{
        fun getInstance(category: DataCategory):NewsFragment{
            var newsFragment = NewsFragment()
            newsFragment.selectedCategory = category
            return newsFragment
        }
    }
    lateinit var selectedCategory : DataCategory
    lateinit var dataBinding : FragmentNewsBinding
    lateinit var viewModel : NewsViewModel
    @Inject lateinit var articleAdapter: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this )[NewsViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dataBinding = DataBindingUtil.inflate(inflater , R.layout.fragment_news , container , false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        subscribeToLiveData()
        articleAdapter.onItemSelectedClicked = object : ArticleAdapter.OnItemSelectedClicked{
            override fun onItemClicked(article: ArticlesItem?, position: Int ) {
                val intent = Intent(requireContext() , NewsDetailsActivity::class.java)
                intent.putExtra("article" , article)
                startActivity(intent)

            }


        }

    }



    fun initViews(){
        dataBinding.recyclerArticle.adapter = articleAdapter
        dataBinding.tabSources.addOnTabSelectedListener(object  : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val source  : SourcesItem =  tab?.tag as SourcesItem
                viewModel.loadArticles(source.id?:"")
                dataBinding.recyclerArticle.layoutManager!!.scrollToPosition(0)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                dataBinding.recyclerArticle.layoutManager!!.scrollToPosition(0)
            }

        })


    }


    fun subscribeToLiveData(){
            viewModel.getSources(selectedCategory)
            viewModel.sourcesResponse.observe(viewLifecycleOwner){source->
                showTabs(source)

            }
        viewModel.articleLiveData.observe(viewLifecycleOwner){article->
            Log.e("Fragment" , article.toString())
                articleAdapter.articles = article as MutableList<ArticlesItem?>?
            articleAdapter.notifyDataSetChanged()
        }

        viewModel.progressBarLiveData.observe(viewLifecycleOwner){
                dataBinding.progressBar.isVisible = it
                }

        }


    private fun showTabs(source: List<SourcesItem?>?) {
        for(i in 0 until source!!.size ){
            var tab = dataBinding.tabSources.newTab()
            tab.text = source.get(i)!!.name
            tab.tag = source.get(i)
            dataBinding.tabSources.addTab(tab)
        }
    }






}


