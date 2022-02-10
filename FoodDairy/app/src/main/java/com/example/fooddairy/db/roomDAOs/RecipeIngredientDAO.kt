package com.example.fooddairy.db.roomDAOs

import androidx.room.*
import com.example.fooddairy.db.model.RecipeIngredient

@Dao
interface RecipeIngredientDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeIngredient(recipeIngredient: RecipeIngredient)

    @Delete
    suspend fun deleteRecipeIngredient(recipeIngredient: RecipeIngredient)

}