package net.hafiznaufalr.recipeapps.ui.detail

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.hafiznaufalr.recipeapps.network.NetworkService
import net.hafiznaufalr.recipeapps.ui.base.BasePresenter

class DetailPresenter: BasePresenter<DetailContract.View>, DetailContract.Presenter {

    var view: DetailContract.View? = null
    private val dataSource = NetworkService.create()

    override fun takeView(view: DetailContract.View) {
        this.view = view
    }

    override fun dropView() {
        this.view = null
    }

    override fun getDataDetailRecipe(idMeal: String) {
        GlobalScope.launch(Dispatchers.Main) {
            view?.showProgress()
            try {
                val data = dataSource.getDetailRecipe(idMeal)
                val response = data.await()
                view?.onDataDetailResponse(response)
                view?.hideProgress()
            }catch (throwable: Throwable){
                view?.onDataDetailFailure(throwable)
                view?.hideProgress()
            }
        }
    }
}