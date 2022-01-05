package com.example.fooddairycompose.repositories

import com.example.fooddairycompose.model.Ingredient

class IngredientRepository() {

    companion object{
        var id: Int = 0
    }

    private val ingredients: MutableList<Ingredient> = mutableListOf()

    init {
        addIngredient(Ingredient(0, "Groundbeef", 400))
        addIngredient(Ingredient(0, "Tomato", 20))
        addIngredient(Ingredient(0, "Onion", 50))
        addIngredient(Ingredient(0, "Garlic", 10))
        addIngredient(Ingredient(0, "Beans", 200))
        addIngredient(Ingredient(0, "Tomatosauce", 150))
        addIngredient(Ingredient(0, "Potato", 50))
        addIngredient(Ingredient(0, "Egg", 100))
        addIngredient(Ingredient(0, "Shrimp", 125))
        addIngredient(Ingredient(0, "Broccoli", 30))
        addIngredient(Ingredient(0, "Chicken", 300))
        addIngredient(Ingredient(0, "Cauliflower", 50))
        addIngredient(Ingredient(0, "Spinach", 20))
    }

    fun getAllIngredients(): List<Ingredient>{
        return ingredients.toList()
    }

    fun addIngredient(ingredient: Ingredient): Ingredient{
        ingredient.id = Companion.id++
        ingredients.add(ingredient)
        return ingredient
    }

    fun getIngredientById(id: Int): Ingredient?{
        return ingredients.find { i -> i.id==id }
    }

    fun updateIngredient(ingredient: Ingredient): Ingredient{
        var newIngredient: Ingredient = getIngredientById(ingredient.id)!!
        //newIngredient.name = ingredient.name
        //newIngredient.calories = ingredient.calories
        newIngredient = ingredient
        return newIngredient
    }

    fun deleteIngredient(ingredient: Ingredient){
        ingredients.remove(ingredient)
    }
}