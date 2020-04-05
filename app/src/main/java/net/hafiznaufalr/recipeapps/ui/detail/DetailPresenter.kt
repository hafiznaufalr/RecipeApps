package net.hafiznaufalr.recipeapps.ui.detail

import kotlinx.coroutines.*
import net.hafiznaufalr.recipeapps.network.NetworkService
import net.hafiznaufalr.recipeapps.ui.base.BasePresenter

class DetailPresenter: BasePresenter<DetailContract.View>, DetailContract.Presenter {

    var view: DetailContract.View? = null
    private val dataSource = NetworkService.create()
    private val job = SupervisorJob()
    private val scope = CoroutineScope(job + Dispatchers.Main)

    override fun takeView(view: DetailContract.View) {
        this.view = view
    }

    override fun dropView() {
        this.view = null
    }

    override fun getDataDetailRecipe(idMeal: String?) {
        scope.launch{
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