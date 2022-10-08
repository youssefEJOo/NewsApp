package com.route.news_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.route.news_app.model.ArticlesItem

class ArticleAdapter (var items : List<ArticlesItem?>? = null): RecyclerView.Adapter<ArticleAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_article , parent ,false)
            return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items!!.get(position) ?: return
        holder.articletexauthor.text = item.author
        holder.articletextitle.text = item.title
        holder.articletexdate.text = item.publishedAt
        Glide.with(holder.itemView)
            .load(item.urlToImage)
            .into(holder.imagearticle)
    }


    override fun getItemCount(): Int {
       return if (items == null ) 0 else items!!.size
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imagearticle = itemView.findViewById<ImageView>(R.id.item_article_image)
        val articletexauthor = itemView.findViewById<TextView>(R.id.item_article_author)
        val articletextitle = itemView.findViewById<TextView>(R.id.item_article_title)
        val articletexdate = itemView.findViewById<TextView>(R.id.item_article_date)

    }
}