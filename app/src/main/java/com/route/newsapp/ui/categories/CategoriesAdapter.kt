package com.route.newsapp.ui.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.route.newsapp.R
import com.route.newsapp.databinding.ItemLeftCategoryBinding
import com.route.newsapp.databinding.ItemRightCategoryBinding
import com.route.newsapp.model.DataCategory

class CategoriesAdapter(var categories : MutableList<DataCategory>? = null) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    val RIGHT_ITEM = 0
    val LEFT_ITEM = 1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == RIGHT_ITEM){
             val view : ItemRightCategoryBinding  = DataBindingUtil.inflate(LayoutInflater.from(parent.context)
                 , R.layout.item_right_category , parent , false)
            return RightCategoryViewHolder(view)
        }else{

            val view : ItemLeftCategoryBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context)
                , R.layout.item_left_category , parent , false )
            return LeftCategoryViewHolder(view)

        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(position % 2 == 0) RIGHT_ITEM else LEFT_ITEM
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val category = categories?.get(position)
        if (getItemViewType(position) == RIGHT_ITEM){
            if (category != null) {
                (holder as RightCategoryViewHolder).bind(category)
            }
        }else{
            if (category != null) {
                (holder as LeftCategoryViewHolder).bind(category)
            }
        }


        holder.itemView.setOnClickListener {
            if (category != null) {
                onCategorySelected?.onItemCategorySelected(category , position)
            }
        }
    }
    var onCategorySelected : OnCategorySelected? = null
    interface OnCategorySelected{
        fun onItemCategorySelected(category : DataCategory , position : Int)
    }

    override fun getItemCount(): Int {
        return categories?.size?:0
    }

    class RightCategoryViewHolder(val view : ItemRightCategoryBinding) : RecyclerView.ViewHolder(view.root){
            fun bind(model : DataCategory){
                    view.model = model
                        view.executePendingBindings()
            }
    }
    class LeftCategoryViewHolder(val view : ItemLeftCategoryBinding) : RecyclerView.ViewHolder(view.root){
            fun bind(model : DataCategory){
                view.model = model
                view.executePendingBindings()
            }
    }

}