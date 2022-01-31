package com.example.fooddairy.db

class IngredientRepository(private val dao: IngredientDAO) {

    val ingredients = dao.getAllIngredients();

    suspend fun insertIngredient(ingredient: Ingredient): Long{
        return dao.insertIngredient(ingredient);
    }

    suspend fun updateIngredient(ingredient: Ingredient): Int{
        return dao.updateIngredient(ingredient);
    }

    suspend fun deleteIngredient(ingredient: Ingredient): Int{
        return dao.deleteIngredient(ingredient);
    }

    suspend fun getIngredientById(ingredientId: Int): Ingredient{
        return dao.getIngredientById(ingredientId)
    }

}