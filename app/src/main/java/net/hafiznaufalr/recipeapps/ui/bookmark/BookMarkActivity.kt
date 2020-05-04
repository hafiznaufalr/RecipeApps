package net.hafiznaufalr.recipeapps.ui.bookmark

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_bookmark.*
import net.hafiznaufalr.recipeapps.R
import net.hafiznaufalr.recipeapps.db.bookmark.BookmarkHelper
import net.hafiznaufalr.recipeapps.model.Filter
import net.hafiznaufalr.recipeapps.ui.base.BaseActivity

class BookMarkActivity: BaseActivity() {
    private lateinit var bookmarkHelper: BookmarkHelper
    private lateinit var adapter: BookmarkAdapter
    private var listBookmark: ArrayList<Filter> = arrayListOf()

    override fun onActivityReady(savedInstanceState: Bundle?) {
        bookmarkHelper = BookmarkHelper.getInstance(this)
        bookmarkHelper.open()
        prepareRv()
        actionBack()
    }

    override fun getLayoutId(): Int = R.layout.activity_bookmark


    private fun actionBack() {
        iv_arrow_back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun prepareRv() {
        listBookmark = bookmarkHelper.getAllRecipe()
        listBookmark.reverse()
        adapter = BookmarkAdapter(this, listBookmark)
        rv_bookmark.adapter = adapter
        if (listBookmark.isEmpty()){
            tv_null.visibility = View.VISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        prepareRv()
    }

}