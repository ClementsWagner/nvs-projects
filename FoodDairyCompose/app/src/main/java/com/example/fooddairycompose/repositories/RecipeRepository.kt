package com.example.fooddairycompose.repositories

import com.example.fooddairycompose.model.Ingredient
import com.example.fooddairycompose.model.Recipe
import com.example.fooddairycompose.model.RecipeIngredient

class RecipeRepository {
    companion object{
        var id: Int = 0
    }

    private val recipes: MutableList<Recipe> = mutableListOf()

    init {
        addRecipe(Recipe(0,"Chili con carne",
            "In a Dutch oven, cook beef over medium heat until no longer pink, 5-7 minutes; crumble beef. Drain and set aside.\n" +
                    "In the same pot, heat oil; saute onions until tender. Add garlic; cook 1 minute longer. Stir in the green pepper, salt, chili powder, bouillon, cayenne, cinnamon, cumin and oregano. Cook for 2 minutes, stirring until combined.\n" +
                    "Add tomatoes and browned beef. Stir in water. Bring to a boil. Reduce heat; cover and simmer for about 1 hour. Add beans and heat through. If desired top with sour cream and jalapeno.",
            listOf(
                RecipeIngredient(Ingredient(0,"Groundbeef", 300), 400f, "gram"),
                RecipeIngredient(Ingredient(0, "Onion", 100), 2f),
                RecipeIngredient(Ingredient(0, "Garlic", 50), 2f),
                RecipeIngredient(Ingredient(0, "Tomatosauce", 100), 500f, "Liter"),
                RecipeIngredient(Ingredient(0, "Beans", 250), 400f, "gram")
            )))
    }

    fun getAllRecipes(): List<Recipe>{
        return recipes.toList()
    }

    fun addRecipe(recipe: Recipe): Recipe{
        recipe.id = Companion.id++
        recipes.add(recipe)
        return recipe
    }

    fun getRecipeById(id: Int): Recipe?{
        return recipes.find { r -> r.id==id }
    }

    fun updateRecipe(recipe: Recipe): Recipe{
        var newRecipe = getRecipeById(recipe.id)
        newRecipe = recipe
        return recipe
    }

}