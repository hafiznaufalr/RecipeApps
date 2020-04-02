package net.hafiznaufalr.recipeapps.ui.category

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_category.*
import net.hafiznaufalr.recipeapps.R
import net.hafiznaufalr.recipeapps.model.Filter
import net.hafiznaufalr.recipeapps.model.FilterResponse
import net.hafiznaufalr.recipeapps.ui.base.BaseActivity

class CategoryActivity : BaseActivity(), CategoryContract.View {
    lateinit var presenter: CategoryContract.Presenter
    lateinit var adapter: CategoryAdapter
    private var listRecipe: MutableList<Filter> = mutableListOf()
    override fun onActivityReady(savedInstanceState: Bundle?) {
        val category = intent.getStringExtra("category")
        tv_title_category.text = category
        setPresenter()
        actionBack()
        prepareRv()
        getData(category)
        refresh(category)
    }


    override fun getLayoutId(): Int = R.layout.activity_category

    private fun refresh(category: String?) {
        swipe_category.setOnRefreshListener {
            getData(category)
        }
    }

    private fun prepareRv() {
        adapter = CategoryAdapter(this, listRecipe)
        rv_recipe.adapter = adapter
    }

    private fun setPresenter() {
        presenter = CategoryPresenter()
        presenter.takeView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
    }

    private fun getData(category: String?) {
        presenter.getDataByCategory(category)
    }


    private fun actionBack() {
        iv_arrow_back.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onDataCategoryResponse(data: FilterResponse) {
        listRecipe.clear()
        listRecipe.addAll(data.meals)
        adapter.notifyDataSetChanged()
    }

    override fun onDataCategoryFailure(throwable: Throwable) {
        Log.e("TAG", throwable.message.toString())
        Toast.makeText(this, getString(R.string.on_failure), Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        swipe_category.isRefreshing = true
    }

    override fun hideProgress() {
        swipe_category.isRefreshing = false
    }

}