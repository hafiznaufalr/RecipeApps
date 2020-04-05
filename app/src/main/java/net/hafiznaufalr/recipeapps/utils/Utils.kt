package net.hafiznaufalr.recipeapps.utils

import net.hafiznaufalr.recipeapps.model.Recipe

fun listIngredients(ingredient: Recipe): MutableList<String> {
    val ingredientsList = mutableListOf<String>()

    if (ingredient.strIngredient1 != "" && ingredient.strIngredient1 != null) ingredientsList.add("1. " + ingredient.strIngredient1 + " : " + ingredient.strMeasure1)
    if (ingredient.strIngredient2 != "" && ingredient.strIngredient2 != null) ingredientsList.add("2. " + ingredient.strIngredient2 + " : " + ingredient.strMeasure2)
    if (ingredient.strIngredient3 != "" && ingredient.strIngredient3 != null) ingredientsList.add("3. " + ingredient.strIngredient3 + " : " + ingredient.strMeasure3)
    if (ingredient.strIngredient4 != "" && ingredient.strIngredient4 != null) ingredientsList.add("4. " + ingredient.strIngredient4 + " : " + ingredient.strMeasure4)
    if (ingredient.strIngredient5 != "" && ingredient.strIngredient5 != null) ingredientsList.add("5. " + ingredient.strIngredient5 + " : " + ingredient.strMeasure5)
    if (ingredient.strIngredient6 != "" && ingredient.strIngredient6 != null) ingredientsList.add("6. " + ingredient.strIngredient6 + " : " + ingredient.strMeasure6)
    if (ingredient.strIngredient7 != "" && ingredient.strIngredient7 != null) ingredientsList.add("7. " + ingredient.strIngredient7 + " : " + ingredient.strMeasure7)
    if (ingredient.strIngredient8 != "" && ingredient.strIngredient8 != null) ingredientsList.add("8. " + ingredient.strIngredient8 + " : " + ingredient.strMeasure8)
    if (ingredient.strIngredient9 != "" && ingredient.strIngredient9 != null) ingredientsList.add("9. " + ingredient.strIngredient9 + " : " + ingredient.strMeasure9)
    if (ingredient.strIngredient10 != "" && ingredient.strIngredient10 != null) ingredientsList.add("10. " + ingredient.strIngredient10 + " : " + ingredient.strMeasure10)
    if (ingredient.strIngredient11 != "" && ingredient.strIngredient11 != null) ingredientsList.add("11. " + ingredient.strIngredient11 + " : " + ingredient.strMeasure11)
    if (ingredient.strIngredient12 != "" && ingredient.strIngredient12 != null) ingredientsList.add("12. " + ingredient.strIngredient12 + " : " + ingredient.strMeasure12)
    if (ingredient.strIngredient13 != "" && ingredient.strIngredient13 != null) ingredientsList.add("13. " + ingredient.strIngredient13 + " : " + ingredient.strMeasure13)
    if (ingredient.strIngredient14 != "" && ingredient.strIngredient14 != null) ingredientsList.add("14. " + ingredient.strIngredient14 + " : " + ingredient.strMeasure14)
    if (ingredient.strIngredient15 != "" && ingredient.strIngredient15 != null) ingredientsList.add("15. " + ingredient.strIngredient15 + " : " + ingredient.strMeasure15)
    if (ingredient.strIngredient16 != "" && ingredient.strIngredient16 != null) ingredientsList.add("16. " + ingredient.strIngredient16 + " : " + ingredient.strMeasure16)
    if (ingredient.strIngredient17 != "" && ingredient.strIngredient17 != null) ingredientsList.add("17. " + ingredient.strIngredient17 + " : " + ingredient.strMeasure17)
    if (ingredient.strIngredient18 != "" && ingredient.strIngredient18 != null) ingredientsList.add("18. " + ingredient.strIngredient18 + " : " + ingredient.strMeasure18)
    if (ingredient.strIngredient19 != "" && ingredient.strIngredient19 != null) ingredientsList.add("19. " + ingredient.strIngredient19 + " : " + ingredient.strMeasure19)
    if (ingredient.strIngredient20 != "" && ingredient.strIngredient20 != null) ingredientsList.add("20. " + ingredient.strIngredient20 + " : " + ingredient.strMeasure20)

    return ingredientsList
}
