package com.example.fooddairy.db.repositories

import com.example.fooddairy.db.RecipeIngredientDAO
import com.example.fooddairy.db.model.RecipeIngredient

class RecipeIngredientRepository(private val dao: RecipeIngredientDAO, private val recipeId: Int) {


    suspend fun insertRecipeIngredient(recipeIngredient: RecipeIngredient){
        dao.insertRecipeIngredient(recipeIngredient)
    }

    suspend fun deleteRecipeIngredient(recipeIngredient: RecipeIngredient){
        dao.deleteRecipeIngredient(recipeIngredient)
    }
}