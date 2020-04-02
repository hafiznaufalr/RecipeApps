package net.hafiznaufalr.recipeapps.ui.category

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_recipe.view.*
import net.hafiznaufalr.recipeapps.R
import net.hafiznaufalr.recipeapps.model.Filter

class CategoryAdapter(
    private val context: Context,
    private val data: List<Filter>
) : RecyclerView.Adapter<CategoryAdapter.MyHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) =
        MyHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_recipe, p0, false))


    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val view = holder.itemView
        view.tv_id_recipe.text = data[position].idMeal
        view.tv_name_recipe.text = data[position].strMeal
        Glide.with(context).load(data[position].strMealThumb).into(view.iv_recipe)
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}