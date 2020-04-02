package net.hafiznaufalr.recipeapps.ui.base

interface BasePresenter<T> {

    fun takeView(view: T)

    fun dropView()
}