package net.hafiznaufalr.recipeapps.db.bookmark

import android.provider.BaseColumns

class BookmarkContract {
    var TABLE_BOOKMARK = "bookmark"
    internal class BookmarkColumns : BaseColumns {
        companion object {
            var IDMEAL = "id_meal"
            var STRMEAL = "str_meal"
            var STRMEALTHUMB = "str_meal_thumb"
        }
    }
}