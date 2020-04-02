package net.hafiznaufalr.recipeapps.ui.main

import net.hafiznaufalr.recipeapps.model.CategoryResponse
import net.hafiznaufalr.recipeapps.model.Recipe
import net.hafiznaufalr.recipeapps.model.RecipeResponse
import net.hafiznaufalr.recipeapps.ui.base.BasePresenter
import net.hafiznaufalr.recipeapps.ui.base.BaseView

interface MainContract {
    interface View : BaseView<Presenter>{
        fun onRandomRecipeResponse(data: RecipeResponse)
        fun onRandomRecipeFailure(throwable: Throwable)

        fun onCategoryResponse(data: CategoryResponse)
        fun onCategoryFailure(throwable: Throwable)

    }

    interface Presenter: BasePresenter<View>{
        fun getDataRecipeRandom()
        fun getDataCategory()
    }
}