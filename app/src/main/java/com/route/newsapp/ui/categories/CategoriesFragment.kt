package com.route.newsapp.ui.categories

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.route.newsapp.R
import com.route.newsapp.databinding.FragmentCategoriesBinding
import com.route.newsapp.model.DataCategory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CategoriesFragment : Fragment() {
    lateinit var dataBinding : FragmentCategoriesBinding
    @Inject lateinit var categoryAdapter: CategoriesAdapter
     var categories  = mutableListOf<DataCategory>(
         DataCategory(id = "sports" , title = "sports" , imageId = R.drawable.sports
             , backGroundColor = R.color.red),
         DataCategory(id = "general" , title = "general" , imageId = R.drawable.politics
             , backGroundColor = R.color.blue),
         DataCategory(id = "health" , title = "health" , imageId = R.drawable.health
             , backGroundColor = R.color.pink),
         DataCategory(id = "business" , title = "business" , imageId = R.drawable.bussines
             , backGroundColor = R.color.brown_orange),
         DataCategory(id = "entertainment" , title = "entertainment" , imageId = R.drawable.environment
             , backGroundColor = R.color.babyBlue),
         DataCategory(id = "science" , title = "science" , imageId = R.drawable.science
             , backGroundColor = R.color.yellow)
     )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dataBinding = DataBindingUtil.inflate(inflater , R.layout.fragment_categories , container , false)
        return dataBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }



    fun initViews(){
        categoryAdapter.categories = categories
        dataBinding.categoryRecyclerView.adapter = categoryAdapter
        categoryAdapter.onCategorySelected = object : CategoriesAdapter.OnCategorySelected{
            override fun onItemCategorySelected(category: DataCategory, position: Int) {
                    onCategoryClicked?.onCategoryClicked(category)
            }

        }
    }
    var onCategoryClicked : OnCategoryClicked ? = null
    interface OnCategoryClicked{
        fun onCategoryClicked(category: DataCategory)
    }
}