package com.example.fooddairy.db

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class RecipeRepository(private val dao: RecipeDAO) {


    val recipes: Flow<List<Recipe>> = dao.getAllRecipes()


    suspend fun insertRecipe(recipe: Recipe): Long{
        return dao.insertRecipe(recipe)
    }

    suspend fun getRecipeDetailsById(recipeId: Int): RecipeWithIngredient{
        return dao.getRecipeDetailsById(recipeId).first()
    }

    suspend fun getRecipeById(recipeId: Int): Recipe{
        return dao.getRecipeById(recipeId)
    }

    fun getIngredientsWithAmount(recipeId: Int) : Flow<List<IngredientWithAmount>> {
       return dao.getIngredientWithAmount(recipeId)
    }

    suspend fun updateRecipe(newRecipe: Recipe){
       dao.insertRecipe(newRecipe)
    }

    suspend fun deleteRecipe(recipe: Recipe){
        dao.deleteRecipe(recipe)
    }
}