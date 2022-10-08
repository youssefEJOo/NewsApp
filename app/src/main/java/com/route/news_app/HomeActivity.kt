package com.route.news_app
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.route.news_app.model.CategoriesModel
import com.route.news_app.ui.CategoriesFragment
import com.route.news_app.ui.NewsFragment
import com.route.news_app.ui.SettingsFragment

class HomeActivity : AppCompatActivity() {


    lateinit var drawerLayout: DrawerLayout
    lateinit var iconDrawer : ImageView
    lateinit var categories : LinearLayout
    lateinit var settings : LinearLayout
    val category : CategoriesFragment = CategoriesFragment()
    val setting : SettingsFragment = SettingsFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initViews()
        showFragment(category)

    }

    private fun initViews() {
        drawerLayout = findViewById(R.id.drawer_layout)
        iconDrawer = findViewById(R.id.ic_drawer)
        categories = findViewById(R.id.categories)
        settings = findViewById(R.id.settings)


        category.onCategoryClicked = object : CategoriesFragment.OnCategoryClicked{
            override fun onFragmentShow(item: CategoriesModel) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container , NewsFragment.getInstance(item))
                    .addToBackStack(null)
                    .commit()
            }


        }
        iconDrawer.setOnClickListener {
            drawerLayout.open()
        }
        categories.setOnClickListener {
            showFragment(category)
            drawerLayout.close()
        }
        settings.setOnClickListener {
            showFragment(setting)
            drawerLayout.close()
        }
    }

    private fun showFragment(fragment:Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }



}