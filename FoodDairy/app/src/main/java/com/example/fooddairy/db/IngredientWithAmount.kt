package com.example.fooddairy.db

import androidx.room.Embedded

data class IngredientWithAmount(
    @Embedded
    val ingredient: Ingredient ,
    val amount: Float)