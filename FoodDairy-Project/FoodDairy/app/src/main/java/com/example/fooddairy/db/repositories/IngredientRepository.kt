package com.example.fooddairy.db.repositories

import com.example.fooddairy.db.roomDAOs.IngredientDAO
import com.example.fooddairy.db.model.Ingredient
import kotlinx.coroutines.flow.Flow

class IngredientRepository(private val dao: IngredientDAO, private val localStorage: Boolean) {

    lateinit var ingredients : Flow<List<Ingredient>>

    init {
        ingredients = dao.getAllIngredients()
    }

    suspend fun insertIngredient(ingredient: Ingredient): Long{
        return dao.insertIngredient(ingredient);
    }

    suspend fun updateIngredient(ingredient: Ingredient): Int{
        return dao.updateIngredient(ingredient);
    }

    suspend fun deleteIngredient(ingredient: Ingredient): Int{
        return dao.deleteIngredient(ingredient);
    }

    suspend fun getIngredientById(ingredientId: Int): Ingredient {
        return dao.getIngredientById(ingredientId)

    }

}