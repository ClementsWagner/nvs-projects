package com.example.fooddairycompose.model

data class Recipe(var id: Int = 0, var name: String, var description: String, var ingredients: List<RecipeIngredient>) {
}