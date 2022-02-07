package com.example.fooddairy.db

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: Recipe): Long

    @Update
    suspend fun updateRecipe(recipe: Recipe): Int

    @Delete
    suspend fun deleteRecipe(recipe: Recipe): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeIngredient(recipeIngredient: RecipeIngredient): Long

    @Query("SELECT * FROM recipe")
    fun getAllRecipes(): Flow<List<Recipe>>

    @Query("SELECT * FROM Recipe WHERE recipeId=:recipeId")
    suspend fun getRecipeById(recipeId: Int): Recipe

    @Transaction
    @Query("SELECT * FROM recipe WHERE :recipeId=recipeId")
    suspend fun getRecipeDetailsById(recipeId: Int): List<RecipeWithIngredient>

    @Transaction
    @Query("SELECT * FROM recipeingredient INNER JOIN ingredient ON recipeingredient.ingredientId = ingredient.ingredientId WHERE recipeingredient.recipeId = :recipeId")
    fun getIngredientWithAmount(recipeId: Int): Flow<List<IngredientWithAmount>>
}