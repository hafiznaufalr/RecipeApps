package net.hafiznaufalr.recipeapps.ui.detail

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.bumptech.glide.Glide
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_detail.*
import net.hafiznaufalr.recipeapps.R
import net.hafiznaufalr.recipeapps.db.bookmark.BookmarkHelper
import net.hafiznaufalr.recipeapps.db.recent.RecentHelper
import net.hafiznaufalr.recipeapps.model.Filter
import net.hafiznaufalr.recipeapps.model.Recent
import net.hafiznaufalr.recipeapps.model.Recipe
import net.hafiznaufalr.recipeapps.model.RecipeResponse
import net.hafiznaufalr.recipeapps.ui.base.BaseActivity
import net.hafiznaufalr.recipeapps.utils.ListViewHelper
import net.hafiznaufalr.recipeapps.utils.listIngredients
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : BaseActivity(), DetailContract.View {
    lateinit var presenter: DetailContract.Presenter
    lateinit var bookmarkHelper: BookmarkHelper
    lateinit var recentHelper: RecentHelper
    lateinit var bookedData: Filter
    lateinit var dialog: SpotsDialog
    lateinit var now: String
    lateinit var recent: Recent

    override fun getLayoutId(): Int = R.layout.activity_detail

    override fun onActivityReady(savedInstanceState: Bundle?) {
        val id = intent.getStringExtra("idMeal")
        dialog = SpotsDialog.Builder().setContext(this).build() as SpotsDialog
        setPresenter()
        sqliteOpenHelper()
        actionBack()
        getData(id)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val date = Calendar.getInstance().time
        now = dateFormat.format(date).toString()
    }

    private fun sqliteOpenHelper() {
        bookmarkHelper = BookmarkHelper.getInstance(this)
        bookmarkHelper.open()

        recentHelper = RecentHelper.getInstance(this)
        recentHelper.open()
    }

    private fun getData(id: String?) {
        presenter.getDataDetailRecipe(id)
    }

    private fun setPresenter() {
        presenter = DetailPresenter()
        presenter.takeView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
    }

    private fun actionBack() {
        ic_arrow_back.setOnClickListener {
            onBackPressed()
        }
    }


    override fun onDataDetailResponse(data: RecipeResponse) {
        val meal = data.meals[0]
        tv_name.text = meal.strMeal
        tv_category.text = meal.strCategory
        tv_country.text = meal.strArea
        tv_instructions.text = meal.strInstructions
        Glide.with(this).load(meal.strMealThumb).into(iv_header)
        showIngredients(meal)
        prepareBookmark(meal)
        displayBookmarkStatus(bookmarkHelper.isBookmarked(meal.idMeal))
        checkView(meal)
    }

    private fun checkView(meal: Recipe) {
        if (recentHelper.isViewed(meal.idMeal)){
            recentHelper.updateDate(meal.idMeal, now)
        }else{
            recent = Recent(
                meal.idMeal,
                meal.strMeal,
                meal.strMealThumb,
                now
            )
            recentHelper.insertRecent(recent)
        }
    }

    private fun displayBookmarkStatus(bookmarked: Boolean) {
        if (bookmarked) {
            iv_bookmark.setImageResource(R.drawable.ic_bookmark_24dp)
        } else {
            iv_bookmark.setImageResource(R.drawable.ic_bookmark_border_24dp)
        }
    }

    private fun prepareBookmark(meal: Recipe) {
        iv_bookmark.setOnClickListener {
            if (bookmarkHelper.isBookmarked(meal.idMeal)) {
                bookmarkHelper.deleteRecipe(meal.idMeal)
                Toast.makeText(
                    this,
                    getString(R.string.remove_bookmark),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                bookedData = Filter(
                    meal.idMeal,
                    meal.strMeal,
                    meal.strMealThumb
                )
                bookmarkHelper.insertRecipe(bookedData)
                Toast.makeText(
                    this,
                    getString(R.string.add_bookmark),
                    Toast.LENGTH_SHORT
                ).show()
            }
            displayBookmarkStatus(bookmarkHelper.isBookmarked(meal.idMeal))
        }
    }

    private fun showIngredients(meal: Recipe) {
        val adapter = ArrayAdapter(
            requireNotNull(this),
            R.layout.item_ingredients,
            listIngredients(meal)
        )

        lv_ingredients.adapter = adapter
        ListViewHelper.getListViewSize(lv_ingredients)

    }

    override fun onDataDetailFailure(throwable: Throwable) {
        Log.e("TAG", throwable.message.toString())
        Toast.makeText(this, getString(R.string.on_failure), Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        dialog.show()
    }

    override fun hideProgress() {
        dialog.dismiss()
    }
}