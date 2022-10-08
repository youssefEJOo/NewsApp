package com.route.news_app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.RecyclerView
import com.route.news_app.CategoriesAdapter
import com.route.news_app.R
import com.route.news_app.model.CategoriesModel


class CategoriesFragment : Fragment() {

    lateinit var categoryRecyclerView : RecyclerView
    var listCategories = listOf<CategoriesModel>(
        CategoriesModel(id = "sports" , title = "sports" , imageId = R.drawable.sports
        , backGroundColor = R.color.red),
        CategoriesModel(id = "general" , title = "general" , imageId = R.drawable.politics
        , backGroundColor = R.color.blue),
        CategoriesModel(id = "health" , title = "health" , imageId = R.drawable.health
        , backGroundColor = R.color.pink),
        CategoriesModel(id = "business" , title = "business" , imageId = R.drawable.bussines
        , backGroundColor = R.color.brown_orange),
        CategoriesModel(id = "entertainment" , title = "entertainment" , imageId = R.drawable.environment
            , backGroundColor = R.color.babyBlue),
        CategoriesModel(id = "science" , title = "science" , imageId = R.drawable.science
            , backGroundColor = R.color.yellow)
    )
    lateinit var categoryAdapter : CategoriesAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

    }
fun initViews(){
    categoryRecyclerView = requireView().findViewById(R.id.category_recycler_view)
    categoryAdapter = CategoriesAdapter(listCategories)
    categoryAdapter.onItemClicked = object : CategoriesAdapter.OnItemClicked{
        override fun onCategoryClicked(pos: Int, item: CategoriesModel) {
            onCategoryClicked?.onFragmentShow(item)
        }

    }
    categoryRecyclerView.adapter = categoryAdapter
}
    var onCategoryClicked : OnCategoryClicked? = null
    interface OnCategoryClicked{
        fun onFragmentShow(item : CategoriesModel)
    }


}