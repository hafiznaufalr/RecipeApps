package net.hafiznaufalr.recipeapps.ui.detail

import android.os.Bundle
import net.hafiznaufalr.recipeapps.R
import net.hafiznaufalr.recipeapps.model.Recipe
import net.hafiznaufalr.recipeapps.ui.base.BaseActivity

class DetailActivity: BaseActivity(), DetailContract.View {
    override fun getLayoutId(): Int = R.layout.activity_detail

    override fun onActivityReady(savedInstanceState: Bundle?) {

    }


    override fun onDataDetailResponse(data: Recipe) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDataDetailFailure(throwable: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}