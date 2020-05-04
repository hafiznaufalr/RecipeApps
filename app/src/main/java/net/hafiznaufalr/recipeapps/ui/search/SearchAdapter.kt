package net.hafiznaufalr.recipeapps.ui.search

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_recipe.view.*
import net.hafiznaufalr.recipeapps.BuildConfig
import net.hafiznaufalr.recipeapps.BuildConfig.PREVIEW
import net.hafiznaufalr.recipeapps.R
import net.hafiznaufalr.recipeapps.db.bookmark.BookmarkHelper
import net.hafiznaufalr.recipeapps.model.Filter
import net.hafiznaufalr.recipeapps.ui.detail.DetailActivity

class SearchAdapter(
    private val context: Context,
    private val data: List<Filter>
) : RecyclerView.Adapter<SearchAdapter.MyHolder>() {

    lateinit var bookmarkHelper: BookmarkHelper

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyHolder {
        bookmarkHelper = BookmarkHelper.getInstance(context)
        bookmarkHelper.open()
        return MyHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_recipe, p0, false))
    }


    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val view = holder.itemView
        view.tv_id_recipe.text = data[position].idMeal
        view.tv_name_recipe.text = data[position].strMeal
        Glide.with(context).load(data[position].strMealThumb + PREVIEW).into(view.iv_recipe)
        prepareBookmark(view, position)
        displayBookmarkStatus(view, bookmarkHelper.isBookmarked(data[position].idMeal))

        view.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("idMeal", data[position].idMeal)
            context.startActivity(intent)
        }
    }

    private fun displayBookmarkStatus(view: View, bookmarked: Boolean) {
        if (bookmarked) {
            view.iv_bookmark.setImageResource(R.drawable.ic_bookmark_24dp)
        } else {
            view.iv_bookmark.setImageResource(R.drawable.ic_bookmark_border_24dp)
        }
    }

    private fun prepareBookmark(view: View, position: Int) {
        view.iv_bookmark.setOnClickListener {
            if (bookmarkHelper.isBookmarked(data[position].idMeal)) {
                bookmarkHelper.deleteRecipe(data[position].idMeal)
                Toast.makeText(
                    context,
                    context.getString(R.string.remove_bookmark),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                bookmarkHelper.insertRecipe(data[position])
                Toast.makeText(
                    context,
                    context.getString(R.string.add_bookmark),
                    Toast.LENGTH_SHORT
                ).show()
            }
            displayBookmarkStatus(view, bookmarkHelper.isBookmarked(data[position].idMeal))
        }
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}