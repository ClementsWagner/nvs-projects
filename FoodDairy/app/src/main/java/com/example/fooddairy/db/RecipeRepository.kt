package com.example.fooddairy.db

import kotlinx.coroutines.flow.Flow

class RecipeRepository(private val dao: RecipeDAO) {


    val recipes = dao.getAllRecipes()


    suspend fun insertRecipe(recipe: Recipe): Long{
        return dao.insertRecipe(recipe)
    }

    suspend fun getRecipeById(recipeId: Int): RecipeWithIngredient{
        return dao.getRecipeDetailsById(recipeId).first()
    }

    suspend fun getIngredientsWithAmount(recipeId: Int) : Flow<List<IngredientWithAmount>> {
       return dao.getIngredientWithAmount(recipeId)
    }


}