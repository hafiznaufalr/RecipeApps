package net.hafiznaufalr.recipeapps.ui.category

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_category.*
import net.hafiznaufalr.recipeapps.R
import net.hafiznaufalr.recipeapps.ui.base.BaseActivity

class CategoryActivity: BaseActivity() {
    override fun onActivityReady(savedInstanceState: Bundle?) {
        val category = intent.getStringExtra("category")
        tv_title_category.text = category
        back()
    }

    override fun getLayoutId(): Int  = R.layout.activity_category


    private fun back() {
        iv_arrow_back.setOnClickListener {
            onBackPressed()
        }
    }

}