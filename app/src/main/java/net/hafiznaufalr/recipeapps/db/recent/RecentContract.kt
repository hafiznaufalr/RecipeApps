package net.hafiznaufalr.recipeapps.db.recent

import android.provider.BaseColumns

class RecentContract {
    var TABLE_RECENT = "recent"
    internal class RecentColumns : BaseColumns {
        companion object {
            var IDMEAL = "id_meal"
            var STRMEAL = "str_meal"
            var STRMEALTHUMB = "str_meal_thumb"
            var TIMEVIEWED = "time_viewed"
        }
    }
}