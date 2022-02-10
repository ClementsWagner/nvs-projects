package com.example.fooddairy.db

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeIngredientDAO {

    @Insert
    suspend fun insertRecipeIngredient(recipeIngredient: RecipeIngredient)

    @Delete
    suspend fun deleteRecipeIngredient(recipeIngredient: RecipeIngredient)

    @Transaction
    @Query("SELECT * FROM recipeingredient INNER JOIN ingredient ON recipeingredient.ingredientId = ingredient.ingredientId WHERE recipeingredient.recipeId = :recipeId")
    fun getIngredientWithAmount(recipeId: Int): LiveData<List<IngredientWithAmount>>
}