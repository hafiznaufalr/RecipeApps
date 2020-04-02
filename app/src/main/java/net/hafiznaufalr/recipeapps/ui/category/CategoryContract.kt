package net.hafiznaufalr.recipeapps.ui.category

import net.hafiznaufalr.recipeapps.model.FilterResponse
import net.hafiznaufalr.recipeapps.ui.base.BasePresenter
import net.hafiznaufalr.recipeapps.ui.base.BaseView

interface CategoryContract {
    interface View: BaseView<Presenter>{
        fun onDataCategoryResponse(data: FilterResponse)
        fun onDataCategoryFailure(throwable: Throwable)
    }

    interface Presenter: BasePresenter<View>{
        fun getDataByCategory(category: String?)
    }
}