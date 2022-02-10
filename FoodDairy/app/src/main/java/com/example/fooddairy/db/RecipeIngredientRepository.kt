package com.example.fooddairy.db

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class RecipeIngredientRepository(private val dao: RecipeIngredientDAO, private val recipeId: Int) {


    suspend fun insertRecipeIngredient(recipeIngredient: RecipeIngredient){
        dao.insertRecipeIngredient(recipeIngredient)
    }

    suspend fun deleteRecipeIngredient(recipeIngredient: RecipeIngredient){
        dao.deleteRecipeIngredient(recipeIngredient)
    }
}