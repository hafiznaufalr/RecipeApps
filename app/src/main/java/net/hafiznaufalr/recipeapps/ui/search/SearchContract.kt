package net.hafiznaufalr.recipeapps.ui.search

import net.hafiznaufalr.recipeapps.model.FilterResponse
import net.hafiznaufalr.recipeapps.ui.base.BasePresenter
import net.hafiznaufalr.recipeapps.ui.base.BaseView

interface SearchContract {
    interface View: BaseView<Presenter>{
        fun onDataSearchResponse(data: FilterResponse)
        fun onDataSearchFailure(throwable: Throwable)
    }

    interface Presenter: BasePresenter<View>{
        fun getDataSearch(search: String?)
    }
}