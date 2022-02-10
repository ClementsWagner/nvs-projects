package com.example.fooddairy.db.roomDAOs

import androidx.room.*
import com.example.fooddairy.db.model.Ingredient
import kotlinx.coroutines.flow.Flow


@Dao
interface IngredientDAO {

    @Insert
    suspend fun insertIngredient(ingredient: Ingredient): Long

    @Update
    suspend fun updateIngredient(ingredient: Ingredient): Int

    @Delete
    suspend fun deleteIngredient(ingredient: Ingredient): Int

    @Query("SELECT * FROM Ingredient")
    fun getAllIngredients(): Flow<List<Ingredient>>

    @Query("SELECT * FROM Ingredient where ingredientId=:ingredientId")
    suspend fun getIngredientById(ingredientId: Int): Ingredient

}