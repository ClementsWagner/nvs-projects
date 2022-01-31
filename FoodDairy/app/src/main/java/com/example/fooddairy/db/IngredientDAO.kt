package com.example.fooddairy.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface IngredientDAO {

    @Insert
    suspend fun insertIngredient(ingredient: Ingredient) : Long

    @Update
    suspend fun updateIngredient(ingredient: Ingredient): Int

    @Delete
    suspend fun deleteIngredient(ingredient: Ingredient): Int

    @Query("SELECT * FROM Ingredient")
    fun getAllIngredients(): Flow<List<Ingredient>>

    @Query("SELECT * FROM Ingredient where ingredient_id=:ingredientId")
    suspend fun getIngredientById(ingredientId: Int): Ingredient

}