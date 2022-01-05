package com.example.fooddairycompose.ui.contentscreens

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.fooddairycompose.R
import com.example.fooddairycompose.model.Ingredient
import com.example.fooddairycompose.model.Recipe
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddairycompose.repositories.RecipeRepository
import com.example.fooddairycompose.ui.components.*

private val recipeRepository: RecipeRepository = RecipeRepository()

@Composable
fun RecipesContent(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Recipes") },
                backgroundColor = colorResource(id = R.color.teal_200)
            )
        },
        content = { RecipeList(navController = navController, recipes = recipeRepository.getAllRecipes()) },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("new_recipe") }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_add_24),
                    contentDescription = "add"
                )
            }
        },
    )
}

@Composable
fun RecipeDetails(navController: NavController, recipeId: Int){
    val recipe: Recipe = recipeRepository.getRecipeById(recipeId)!!
    Scaffold(topBar = { EditTopAppBar(title = recipe.name,
            onEdit = { navController.navigate("edit_recipe/${recipe.id}") },
            onBack = { navController.popBackStack() },
            onDelete = {/*TODO*/})},
        content = {
        Column(
            modifier = Modifier
                .padding(15.dp)
        ) {
            RecipeIngredientList(recipeIngredients = recipe.ingredients)
            Text(text = recipe.description)
        }
    })
}

@Composable
fun EditRecipe(navController: NavController, recipeId: Int, new: Boolean){
    val recipe: Recipe = if(new) Recipe(0,"","", listOf()) else recipeRepository.getRecipeById(recipeId)!!
    Box(modifier = Modifier
        .padding(bottom = 45.dp)) {
        Scaffold(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            content = {
                Column(
                    modifier = Modifier
                        .padding(15.dp)
                        .padding(bottom = 60.dp)
                ) {
                    TextField(modifier = Modifier.padding(bottom = 30.dp),
                        label = { Text(text = "Recipe Name:") },
                        value = recipe.name,
                        onValueChange = {})
                    Column(modifier = Modifier.padding(bottom = 30.dp)){
                        Text(text = "Ingredients:", fontSize = 18.sp)
                        RecipeIngredientList(recipeIngredients = recipe.ingredients)
                    }

                    TextField(
                        label = { Text(text = "Description") },
                        value = recipe.description,
                        onValueChange = {})
                    SaveOrCancel(
                        onSave = {

                        },
                        onCancel = {navController.popBackStack()})
                }
            },
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = { FloatingActionButton(onClick = {}) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_add_24), contentDescription = "add") }
            }
        )
    }
}
