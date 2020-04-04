package net.hafiznaufalr.recipeapps.ui.search

import android.app.PendingIntent.getActivity
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_search.*
import net.hafiznaufalr.recipeapps.R
import net.hafiznaufalr.recipeapps.model.Filter
import net.hafiznaufalr.recipeapps.model.FilterResponse
import net.hafiznaufalr.recipeapps.ui.base.BaseActivity


class SearchActivity: BaseActivity(), SearchContract.View {
    lateinit var presenter: SearchContract.Presenter
    lateinit var adapter: SearchAdapter
    private var listData: MutableList<Filter> = mutableListOf()

    override fun onActivityReady(savedInstanceState: Bundle?) {
        setPresenter()
        actionClick()
        searchMovies()
        refresh()
        prepareRv()
        showPlaceholder()
        et_search.requestFocus()
    }

    override fun getLayoutId(): Int = R.layout.activity_search

    private fun searchMovies() {
        et_search.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    val keyword = et_search.text.toString()
                    doSearch(keyword)
                    et_search.clearFocus()
                    val `in` = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    `in`.hideSoftInputFromWindow(et_search.windowToken, 0)
                    return true
                }
                return false
            }
        })


        et_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if(et_search.text!!.isEmpty()){
                    listData.clear()
                    adapter.notifyDataSetChanged()
                    showPlaceholder()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    private fun doSearch(keyword: String) {
        if (keyword.isEmpty()){
            listData.clear()
            adapter.notifyDataSetChanged()
            hideProgress()
        }else{
            presenter.getDataSearch(keyword)
        }
    }

    private fun refresh() {
        swipe_search.setOnRefreshListener {
            val keyword = et_search.text.toString()
            doSearch(keyword)
        }
    }


    private fun actionClick() {
        ic_arrow_back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun prepareRv() {
        adapter = SearchAdapter(this, listData)
        rv_search.adapter = adapter
    }

    private fun setPresenter() {
        presenter = SearchPresenter()
        presenter.takeView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
    }

    override fun onDataSearchResponse(data: FilterResponse) {
        listData.clear()
        listData.addAll(data.meals)
        adapter.notifyDataSetChanged()
        showPlaceholder()
    }

    private fun showPlaceholder(){
        if (listData.isEmpty()){
            placeholder.visibility = View.VISIBLE
        }else{
            placeholder.visibility = View.GONE
        }
    }

    override fun onDataSearchFailure(throwable: Throwable) {
        Log.e("TAG", throwable.message.toString())
        Toast.makeText(this, getString(R.string.no_data), Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        swipe_search.isRefreshing = true
    }

    override fun hideProgress() {
        swipe_search.isRefreshing = false

    }
}