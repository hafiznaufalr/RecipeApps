package net.hafiznaufalr.recipeapps.ui.search

import kotlinx.coroutines.*
import net.hafiznaufalr.recipeapps.network.NetworkService
import net.hafiznaufalr.recipeapps.ui.base.BasePresenter

class SearchPresenter: BasePresenter<SearchContract.View>, SearchContract.Presenter {
    var view: SearchContract.View? = null
    private val dataSource = NetworkService.create()
    private val job = SupervisorJob()
    private val scope = CoroutineScope(job + Dispatchers.Main)

    override fun takeView(view: SearchContract.View) {
        this.view = view
    }

    override fun dropView() {
        this.view = null
    }

    override fun getDataSearch(search: String?) {
        scope.launch{
            view?.showProgress()
            try {
                val data = dataSource.searchRecipe(search)
                val response = data.await()
                view?.onDataSearchResponse(response)
                view?.hideProgress()
            }catch (throwable: Throwable){
                view?.onDataSearchFailure(throwable)
                view?.hideProgress()
            }
        }
    }


}