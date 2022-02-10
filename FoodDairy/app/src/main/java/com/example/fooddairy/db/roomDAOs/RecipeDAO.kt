package com.example.fooddairy.db.roomDAOs

import androidx.room.*
import com.example.fooddairy.db.model.IngredientWithAmount
import com.example.fooddairy.db.model.Recipe
import com.example.fooddairy.db.model.RecipeIngredient
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
    @Query("SELECT * FROM recipeingredient INNER JOIN ingredient ON recipeingredient.ingredientId = ingredient.ingredientId WHERE recipeingredient.recipeId = :recipeId")
    fun getIngredientWithAmount(recipeId: Int): Flow<List<IngredientWithAmount>>

    @Transaction
    @Query("DELETE FROM recipeingredient WHERE recipeId=:recipeId")
    suspend fun deleteRecipeIngredients(recipeId: Int)

    @Transaction
    @Query("DELETE FROM recipeingredient WHERE recipeId=:recipeId and ingredientId=:ingredientId")
    suspend fun deleteIngredientFromRecipe(recipeId: Int, ingredientId: Int)
}