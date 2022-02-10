package com.example.fooddairy.db

import androidx.room.*
import com.example.fooddairy.db.model.RecipeIngredient

@Dao
interface RecipeIngredientDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeIngredient(recipeIngredient: RecipeIngredient)

    @Delete
    suspend fun deleteRecipeIngredient(recipeIngredient: RecipeIngredient)

}