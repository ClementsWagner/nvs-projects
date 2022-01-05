package com.example.fooddairycompose.model

data class Ingredient(var id: Int = 0, var name: String, var calories: Int) {
    override fun toString(): String {
        return name
    }
}