package net.hafiznaufalr.recipeapps.ui.detail

import net.hafiznaufalr.recipeapps.model.Recipe
import net.hafiznaufalr.recipeapps.ui.base.BasePresenter
import net.hafiznaufalr.recipeapps.ui.base.BaseView

interface DetailContract {
    interface View: BaseView<Presenter>{
        fun onDataDetailResponse(data: Recipe)
        fun onDataDetailFailure(throwable: Throwable)

    }

    interface Presenter: BasePresenter<View>{
        fun getDataDetailRecipe(idMeal: String)
    }
}