package net.hafiznaufalr.recipeapps.ui.detail

import net.hafiznaufalr.recipeapps.ui.base.BasePresenter
import net.hafiznaufalr.recipeapps.ui.base.BaseView

interface DetailContract {
    interface View: BaseView<Presenter>{
        fun onDataDetailResponse()
        fun onDataDetailFailure()

    }

    interface Presenter: BasePresenter<View>{
        fun getDataDetailRecipe()
    }
}