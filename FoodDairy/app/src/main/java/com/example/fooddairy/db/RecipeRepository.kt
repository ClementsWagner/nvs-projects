package com.example.fooddairy.db

class RecipeRepository(private val dao: RecipeDAO) {


    val recipes = dao.getAllRecipes()


    suspend fun insertRecipe(recipe: Recipe): Long{
        return dao.insertRecipe(recipe)
    }
/*
    suspend fun getRecipeById(recipeId: Int): RecipeWithIngredient{
        return dao.getRecipeById(recipeId)
    }

     */


}