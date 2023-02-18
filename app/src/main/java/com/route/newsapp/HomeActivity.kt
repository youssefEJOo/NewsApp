package com.route.newsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.route.newsapp.databinding.ActivityHomeBinding
import com.route.newsapp.model.DataCategory
import com.route.newsapp.ui.categories.CategoriesFragment
import com.route.newsapp.ui.news.NewsFragment
import com.route.newsapp.ui.settings.SettingsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    lateinit var dataBinding : ActivityHomeBinding
    var settingFragment : SettingsFragment = SettingsFragment()
    var categoriesFragment : CategoriesFragment = CategoriesFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this , R.layout.activity_home)
        showFragment(categoriesFragment)
        initViews()



    }

    fun initViews(){
        categoriesFragment.onCategoryClicked = object : CategoriesFragment.OnCategoryClicked{
            override fun onCategoryClicked(category: DataCategory) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.framelayout , NewsFragment.getInstance(category))
                    .addToBackStack(null)
                    .commit()
                dataBinding.appBarHome.appbarName.text = category.id

            }

        }
        dataBinding.appBarHome.icDrawer.setOnClickListener {
            dataBinding.drawerLayout.open()
        }
        dataBinding.categories.setOnClickListener {
            showFragment(categoriesFragment)
            dataBinding.drawerLayout.close()
        }
        dataBinding.setting.setOnClickListener {
            showFragment(settingFragment)
            dataBinding.drawerLayout.close()
        }

    }

    fun showFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.framelayout ,fragment)
            .addToBackStack(null)
            .commit()
    }

}