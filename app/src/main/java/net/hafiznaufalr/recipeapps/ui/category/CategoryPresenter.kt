package net.hafiznaufalr.recipeapps.ui.category

import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.hafiznaufalr.recipeapps.network.NetworkService
import net.hafiznaufalr.recipeapps.ui.base.BasePresenter

class CategoryPresenter: BasePresenter<CategoryContract.View>, CategoryContract.Presenter {
    var view: CategoryContract.View? = null
    private val dataSource = NetworkService.create()

    override fun takeView(view: CategoryContract.View) {
        this.view = view
    }

    override fun dropView() {
        this.view = null
    }

    override fun getDataByCategory(category: String?) {
        GlobalScope.launch(Dispatchers.Main) {
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