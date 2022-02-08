package com.example.fooddairy.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert

@Dao
interface RecipeIngredientDAO {

    @Insert
    suspend fun insertRecipeIngredient(recipeIngredient: RecipeIngredient)

    @Delete
    suspend fun deleteRecipeIngredient(recipeIngredient: RecipeIngredient)

}