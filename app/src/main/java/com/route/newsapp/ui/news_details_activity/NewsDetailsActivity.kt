package com.route.newsapp.ui.news_details_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider



import com.route.newsapp.R
import com.route.newsapp.databinding.ActivityNewsDetailsBinding
import com.route.newsapp.model.ArticlesItem

class NewsDetailsActivity : AppCompatActivity() {

    lateinit var article : ArticlesItem
    lateinit var viewModel: NewsDetailsViewModel
    lateinit var dataBinding : ActivityNewsDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[NewsDetailsViewModel::class.java]
        dataBinding = DataBindingUtil.setContentView(this , R.layout.activity_news_details)
        dataBinding.binding = this
        article = intent.getSerializableExtra("article") as ArticlesItem






    }
}