package net.hafiznaufalr.recipeapps.utils

import android.util.Log
import android.widget.ListView

object ListViewHelper {
    fun getListViewSize(myListView: ListView) {
        val myListAdapter = myListView.adapter
            ?: //do nothing return null
            return
        //set listAdapter in loop for getting final size
        var totalHeight = 0
        for (size in 0 until myListAdapter.count) {
            val listItem = myListAdapter.getView(size, null, myListView)
            listItem.measure(0, 0)
            totalHeight += listItem.measuredHeight
        }
        //setting listview item in adapter
        val params = myListView.layoutParams
        params.height =
            totalHeight + myListView.dividerHeight * (myListAdapter.count - 1)
        myListView.layoutParams = params

    }
}
