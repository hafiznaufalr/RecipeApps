package net.hafiznaufalr.recipeapps.ui.search

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.hafiznaufalr.recipeapps.network.NetworkService
import net.hafiznaufalr.recipeapps.ui.base.BasePresenter

class SearchPresenter: BasePresenter<SearchContract.View>, SearchContract.Presenter {
    var view: SearchContract.View? = null
    private val dataSource = NetworkService.create()

    override fun takeView(view: SearchContract.View) {
        this.view = view
    }

    override fun dropView() {
        this.view = null
    }

    override fun getDataSearch(search: String?) {
        GlobalScope.launch(Dispatchers.Main) {
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