package com.route.newsapp.ui.news

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.route.newsapp.R
import com.route.newsapp.databinding.ItemArticleBinding
import com.route.newsapp.model.ArticlesItem

class ArticleAdapter (var articles : MutableList<ArticlesItem?>? = null): RecyclerView.Adapter<ArticleAdapter.ArticledViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticledViewHolder {
                val dataBinding : ItemArticleBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context)
            , R.layout.item_article , parent , false )
        return ArticledViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: ArticledViewHolder, position: Int) {
        val article = articles?.get(position)
            holder.binding.ai = article

            holder.binding.parent.setOnClickListener {
                onItemSelectedClicked?.onItemClicked(article , position )
            }
    }

    var onItemSelectedClicked : OnItemSelectedClicked ? = null
    interface OnItemSelectedClicked{
        fun onItemClicked(article : ArticlesItem? , position: Int )
    }

    override fun getItemCount(): Int {
        return articles?.size?:0
    }

    class ArticledViewHolder(val binding : ItemArticleBinding) : RecyclerView.ViewHolder(binding.root){

    }
}