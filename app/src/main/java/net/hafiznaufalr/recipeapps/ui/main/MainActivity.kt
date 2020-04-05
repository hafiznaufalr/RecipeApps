package net.hafiznaufalr.recipeapps.ui.main

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.tv_name
import net.hafiznaufalr.recipeapps.R
import net.hafiznaufalr.recipeapps.db.recent.RecentHelper
import net.hafiznaufalr.recipeapps.model.Category
import net.hafiznaufalr.recipeapps.model.CategoryResponse
import net.hafiznaufalr.recipeapps.model.Filter
import net.hafiznaufalr.recipeapps.model.RecipeResponse
import net.hafiznaufalr.recipeapps.ui.base.BaseActivity
import net.hafiznaufalr.recipeapps.ui.bookmark.BookMarkActivity
import net.hafiznaufalr.recipeapps.ui.detail.DetailActivity
import net.hafiznaufalr.recipeapps.ui.search.SearchActivity


class MainActivity : BaseActivity(), MainContract.View {
    lateinit var presenter: MainContract.Presenter
    lateinit var adapter: CategoryRecipeAdapter
    lateinit var recentAdapter: RecentViewAdapter
    lateinit var recentHelper: RecentHelper
    private var listCategory: MutableList<Category> = mutableListOf()
    private var listRecent: ArrayList<Filter> = arrayListOf()

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onActivityReady(savedInstanceState: Bundle?) {
        setPresenter()
        sqliteOpenHelper()
        getData()
        prepareRv()
        prepareRvRecent()
        refresh()
        doMove()
        clearAll()
    }


    private fun sqliteOpenHelper() {
        recentHelper = RecentHelper.getInstance(this)
        recentHelper.open()

    }

    private fun prepareRvRecent() {
        listRecent = recentHelper.getAllRecent()
        listRecent.reverse()
        recentAdapter = RecentViewAdapter(this, listRecent)
        rv_recently_viewed.adapter = recentAdapter
        if (listRecent.isEmpty()) {
            tv_no_recent.visibility = View.VISIBLE
            tv_clear.visibility = View.GONE
        }else{
            tv_no_recent.visibility = View.GONE
            tv_clear.visibility = View.VISIBLE
        }
    }

    private fun clearAll() {
        tv_clear.setOnClickListener {
            val dialogClickListener =
                DialogInterface.OnClickListener { _, which ->
                    when (which) {
                        DialogInterface.BUTTON_POSITIVE -> {
                            recentHelper.clearRecent()
                            Toast.makeText(this, getString(R.string.clear_recent), Toast.LENGTH_SHORT).show()
                            prepareRvRecent()
                        }
                        DialogInterface.BUTTON_NEGATIVE -> {
                        }
                    }
                }
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setMessage("Are you sure want to clear recent ?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show()

        }


    }


    override fun onResume() {
        super.onResume()
        prepareRvRecent()
    }

    private fun doMove() {
        et_search.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

        iv_fav.setOnClickListener {
            val intent = Intent(this, BookMarkActivity::class.java)
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
        val meal = data.meals[0]
        tv_name.text = meal.strMeal
        Glide.with(this).load(meal.strMealThumb).placeholder(R.drawable.placeholder).into(iv_random)
        cv_random.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("idMeal", meal.idMeal)
            startActivity(intent)
        }
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
