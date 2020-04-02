package net.hafiznaufalr.recipeapps.ui.main

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.hafiznaufalr.recipeapps.network.NetworkService
import net.hafiznaufalr.recipeapps.ui.base.BasePresenter

class MainPresenter: BasePresenter<MainContract.View>, MainContract.Presenter {

    var view: MainContract.View? = null
    private val dataSource = NetworkService.create()

    override fun takeView(view: MainContract.View) {
        this.view = view
    }

    override fun dropView() {
        this.view = null
    }

    override fun getDataRecipeRandom() {
        GlobalScope.launch(Dispatchers.Main) {
            view?.showProgress()
            try {
                val data = dataSource.getRandomRecipe()
                val response = data.await()
                view?.onRandomRecipeResponse(response)
                view?.hideProgress()
            }catch (throwable: Throwable){
                view?.onRandomRecipeFailure(throwable)
                view?.hideProgress()
            }
        }
    }

    override fun getDataCategory() {
        GlobalScope.launch(Dispatchers.Main) {
            view?.showProgress()
            try {
                val data = dataSource.getCategoriesRecipe()
                val response = data.await()
                view?.onCategoryResponse(response)
                view?.hideProgress()
            }catch (throwable: Throwable){
                view?.onCategoryFailure(throwable)
                view?.hideProgress()
            }
        }
    }
}