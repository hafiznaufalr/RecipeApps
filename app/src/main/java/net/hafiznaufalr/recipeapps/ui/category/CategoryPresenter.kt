package net.hafiznaufalr.recipeapps.ui.category

import android.widget.Toast
import kotlinx.coroutines.*
import net.hafiznaufalr.recipeapps.network.NetworkService
import net.hafiznaufalr.recipeapps.ui.base.BasePresenter

class CategoryPresenter: BasePresenter<CategoryContract.View>, CategoryContract.Presenter {
    var view: CategoryContract.View? = null
    private val dataSource = NetworkService.create()
    private val job = SupervisorJob()
    private val scope = CoroutineScope(job + Dispatchers.Main)

    override fun takeView(view: CategoryContract.View) {
        this.view = view
    }

    override fun dropView() {
        this.view = null
    }

    override fun getDataByCategory(category: String?) {
        scope.launch {
            view?.showProgress()
            try {
                val data = dataSource.getRecipeByCategory(category)
                val response = data.await()
                view?.onDataCategoryResponse(response)
                view?.hideProgress()
            }catch (throwable: Throwable){
                view?.onDataCategoryFailure(throwable)
                view?.hideProgress()
            }
        }
    }

}