package com.example.fooddairy.db

class RecipeIngredientRepository(private val dao: RecipeIngredientDAO) {

    suspend fun insertRecipeIngredient(recipeIngredient: RecipeIngredient){
        dao.insertRecipeIngredient(recipeIngredient)
    }

    suspend fun deleteRecipeIngredient(recipeIngredient: RecipeIngredient){
        dao.deleteRecipeIngredient(recipeIngredient)
    }
}