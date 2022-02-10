package com.example.fooddairy.db.repositories

import com.example.fooddairy.db.roomDAOs.RecipeDAO
import com.example.fooddairy.db.model.IngredientWithAmount
import com.example.fooddairy.db.model.Recipe
import kotlinx.coroutines.flow.Flow

class RecipeRepository(private val dao: RecipeDAO) {


    val recipes: Flow<List<Recipe>> = dao.getAllRecipes()


    suspend fun insertRecipe(recipe: Recipe): Long{
        return dao.insertRecipe(recipe)
    }

    suspend fun updateRecipe(newRecipe: Recipe){
        dao.insertRecipe(newRecipe)
    }

    suspend fun deleteRecipe(recipe: Recipe) : Int{
        dao.deleteRecipeIngredients(recipe.recipeId)
        return dao.deleteRecipe(recipe)
    }

    suspend fun deleteIngredientFromRecipe(recipeId: Int, ingredientId: Int){
        dao.deleteIngredientFromRecipe(recipeId, ingredientId)
    }

    suspend fun getRecipeById(recipeId: Int): Recipe {
        return dao.getRecipeById(recipeId)
    }

    fun getIngredientsWithAmount(recipeId: Int) : Flow<List<IngredientWithAmount>> {
       return dao.getIngredientWithAmount(recipeId)
    }
}