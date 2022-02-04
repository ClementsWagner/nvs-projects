package com.example.roomrelationtest

import androidx.room.Entity

@Entity(primaryKeys = ["recipeId", "ingredientId"])
data class RecipeIngredient(
    var recipeId: Int,
    var ingredientId: Int
)