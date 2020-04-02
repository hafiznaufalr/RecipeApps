package net.hafiznaufalr.recipeapps.network


import kotlinx.coroutines.Deferred
import net.hafiznaufalr.recipeapps.model.Recipe
import net.hafiznaufalr.recipeapps.model.RecipeResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("api/json/v1/1/random.php")
    fun getRandomRecipe(): Deferred<RecipeResponse>
}