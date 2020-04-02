package net.hafiznaufalr.recipeapps.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import net.hafiznaufalr.recipeapps.R
import net.hafiznaufalr.recipeapps.model.Category
import net.hafiznaufalr.recipeapps.model.CategoryResponse
import net.hafiznaufalr.recipeapps.model.RecipeResponse
import net.hafiznaufalr.recipeapps.ui.base.BaseActivity
import net.hafiznaufalr.recipeapps.ui.search.SearchActivity

class MainActivity : BaseActivity(), MainContract.View {
    lateinit var presenter: MainContract.Presenter
    lateinit var adapter: CategoryRecipeAdapter
    private var listCategory: MutableList<Category> = mutableListOf()
    override fun onActivityReady(savedInstanceState: Bundle?) {
        setPresenter()
        getData()
        prepareRv()
        refresh()
        gotoSearch()
    }

    override fun getLayoutId(): Int = R.layout.activity_main


    private fun gotoSearch() {
        et_search.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
    }

    private fun prepareRv() {
        adapter = CategoryRecipeAdapter(this, listCategory)
        rv_category.adapter = adapter
    }

    private fun setPresenter() {
        presenter = MainPresenter()
        presenter.takeView(this)
    }

    private fun refresh() {
        swipe_main.setOnRefreshListener {
            getData()
        }
    }

    private fun getData() {
        presenter.getDataCategory()
        presenter.getDataRecipeRandom()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
    }


    override fun onRandomRecipeResponse(data: RecipeResponse) {
        tv_name.text = data.meals[0].strMeal
        Glide.with(this).load(data.meals[0].strMealThumb).into(iv_random)
    }

    override fun onRandomRecipeFailure(throwable: Throwable) {
        Log.e("TAG", throwable.message.toString())
        Toast.makeText(this, getString(R.string.on_failure), Toast.LENGTH_SHORT).show()
    }

    override fun onCategoryResponse(data: CategoryResponse) {
        listCategory.clear()
        listCategory.addAll(data.categories)
        adapter.notifyDataSetChanged()
    }

    override fun onCategoryFailure(throwable: Throwable) {
        Log.e("TAG", throwable.message.toString())
        Toast.makeText(this, getString(R.string.on_failure), Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        swipe_main.isRefreshing = true
    }

    override fun hideProgress() {
        swipe_main.isRefreshing = false
    }
}
