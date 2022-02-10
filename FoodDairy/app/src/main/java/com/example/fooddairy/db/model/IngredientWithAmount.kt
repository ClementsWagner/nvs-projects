package com.example.fooddairy.db.model

import androidx.room.Embedded
import com.example.fooddairy.db.model.Ingredient

data class IngredientWithAmount(
    @Embedded
    val ingredient: Ingredient,
    val amount: Float)