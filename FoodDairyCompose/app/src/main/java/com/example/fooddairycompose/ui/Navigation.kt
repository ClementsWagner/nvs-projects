package com.example.fooddairycompose.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.fooddairycompose.model.Ingredient
import com.example.fooddairycompose.model.Recipe
import com.example.fooddairycompose.model.RecipeIngredient
import com.example.fooddairycompose.repositories.IngredientRepository
import com.example.fooddairycompose.repositories.RecipeRepository
import com.example.fooddairycompose.ui.bottomnav.BottomNavItem
import com.example.fooddairycompose.ui.bottomnav.IngredientScreen
import com.example.fooddairycompose.ui.bottomnav.RecipeScreen
import com.example.fooddairycompose.ui.contentscreens.*

private val recipe: Recipe = Recipe(0,"Chili con carne",
    "In a Dutch oven, cook beef over medium heat until no longer pink, 5-7 minutes; crumble beef. Drain and set aside.\n" +
            "In the same pot, heat oil; saute onions until tender. Add garlic; cook 1 minute longer. Stir in the green pepper, salt, chili powder, bouillon, cayenne, cinnamon, cumin and oregano. Cook for 2 minutes, stirring until combined.\n" +
            "Add tomatoes and browned beef. Stir in water. Bring to a boil. Reduce heat; cover and simmer for about 1 hour. Add beans and heat through. If desired top with sour cream and jalapeno.",
    listOf(RecipeIngredient(Ingredient(0,"Groundbeef", 300), 400f, "gram"),
        RecipeIngredient(Ingredient(0, "Onion", 100), 2f),
        RecipeIngredient(Ingredient(0, "Garlic", 50), 2f),
        RecipeIngredient(Ingredient(0, "Tomatosauce", 100), 500f, "Liter"),
        RecipeIngredient(Ingredient(0, "Beans", 250), 400f, "gram")
    ))

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Recipes.screen_route) {
        composable(BottomNavItem.Recipes.screen_route) {
            RecipeScreen(navController = navController,
            recipes = listOf(recipe))
        }
        composable(BottomNavItem.Ingredients.screen_route) {
            IngredientScreen(navController = navController)
        }
        composable("ingredient_details/{ingredientId}",
            arguments = listOf(
                navArgument("ingredientId") {type = NavType.IntType}
            )){
            val id = it.arguments?.getInt("ingredientId")
            IngredientDetails(navController = navController, ingredientId = id!!)
        }
        composable("edit_ingredient/{ingredientId}",
            arguments = listOf(
                navArgument("ingredientId") {type = NavType.IntType},
            )){
            val id = it.arguments?.getInt("ingredientId")
            EditIngredient(navController = navController, ingredientId = id!!, new = false)
        }
        composable("new_ingredient"){
            EditIngredient(navController = navController,ingredientId = 0, new = true)
        }
        composable("recipe_details/{recipeId}",
            arguments = listOf(
                navArgument("recipeId") {type = NavType.IntType}
            )){
            val id = it.arguments?.getInt("recipeId")
            RecipeDetails(navController = navController, id!!)
        }
        composable("edit_recipe/{recipeId}",
            arguments = listOf(
                navArgument("recipeId") {type = NavType.IntType}
            )){
            val id = it.arguments?.getInt("recipeId")
            EditRecipe(navController = navController, id!!, false)
        }
        composable("new_recipe"){
            EditRecipe(navController = navController, 0, true)
        }
        composable("add_ingredient_recipe"){
            AddIngredientToRecipe(navController = navController)
        }

    }
}