package net.hafiznaufalr.recipeapps.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import net.hafiznaufalr.recipeapps.R
import net.hafiznaufalr.recipeapps.model.Recipe
import net.hafiznaufalr.recipeapps.model.RecipeResponse
import net.hafiznaufalr.recipeapps.ui.base.BaseActivity

class MainActivity : BaseActivity(), MainContract.View {
    lateinit var presenter: MainContract.Presenter
    override fun onActivityReady(savedInstanceState: Bundle?) {
        setPresenter()
        getRandomRecipe()
        refresh()
    }

    private fun setPresenter() {
        presenter = MainPresenter()
        presenter.takeView(this)
    }

    private fun refresh() {
        swipe_main.setOnRefreshListener {
            getRandomRecipe()
        }
    }

    private fun getRandomRecipe() {
        presenter.getDataRecipeRandom()
    }



    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onRandomRecipeResponse(data: RecipeResponse) {
        tv_name.text = data.meals[0].strMeal
        Glide.with(this).load(data.meals[0].strMealThumb).into(iv_random)
    }

    override fun onRandomRecipeFailure(throwable: Throwable) {
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
