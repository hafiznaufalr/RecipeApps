package net.hafiznaufalr.recipeapps.db

import android.provider.BaseColumns

class RecipeContract {
    var TABLE_RECIPE = "recipe"
    internal class MovieColumns : BaseColumns {
        companion object {
            var IDMEAL = "id_meal"
            var STRMEAL = "str_meal"
            var STRMEALTHUMB = "str_meal_thumb"
        }
    }
}