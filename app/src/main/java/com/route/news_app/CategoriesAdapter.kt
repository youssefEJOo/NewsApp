package com.route.news_app

import android.icu.util.ULocale
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.route.news_app.model.CategoriesModel
import java.util.*

class CategoriesAdapter (var category: List<CategoriesModel>): RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>(){

    val RIGHT_SIDED_CODE = 2
        val LEFT_SIDED_CODE = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        var view : View? = null
        if (viewType == RIGHT_SIDED_CODE) {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.left_sided_category, parent, false)
        }else
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.right_sided_category, parent, false)

        return CategoriesViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position %2 == 0) RIGHT_SIDED_CODE else LEFT_SIDED_CODE
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        var item = category.get(position)
        holder.categoryTextView.text = item.title
        holder.CategoryImage.setImageResource(item.imageId)
        holder.materialCard.setBackgroundColor(ContextCompat.getColor(holder.itemView.context , item.backGroundColor))
        holder.itemView.setOnClickListener {
            onItemClicked?.onCategoryClicked(position , item)
        }
    }
    var onItemClicked : OnItemClicked? = null
    interface OnItemClicked{
        fun onCategoryClicked(pos: Int , item: CategoriesModel)
    }
    override fun getItemCount(): Int {
        return category.size
    }
    class CategoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val CategoryImage : ImageView = itemView.findViewById(R.id.category_image)
        val categoryTextView : TextView = itemView.findViewById(R.id.category_text_view)
        val materialCard : MaterialCardView = itemView.findViewById(R.id.material_card_parent)
    }
}