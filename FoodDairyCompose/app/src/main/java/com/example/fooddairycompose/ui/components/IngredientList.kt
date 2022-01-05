package com.example.fooddairycompose.ui.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fooddairycompose.R
import com.example.fooddairycompose.model.Ingredient
import com.example.fooddairycompose.model.RecipeIngredient

@Composable
fun IngredientList(navController: NavController, ingredients: List<Ingredient>){
    LazyColumn {
        items(ingredients) { ingredient -> IngredientListItem(navController = navController, ingredient) }
    }
}



@Composable
fun IngredientListItem(navController: NavController, ingredient: Ingredient){
    Card( modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)
        .clickable { navController.navigate("ingredient_details/${ingredient.id}") },
        elevation = 10.dp) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Text(text = ingredient.name)
        }
    }
}

@Composable
fun RecipeIngredientList(recipeIngredients: List<RecipeIngredient>){
    Column(){
        recipeIngredients.forEach { recipeIngredient -> RecipeIngredientListItem(recipeIngredient) }
    }
}

@Composable
fun RecipeIngredientListItem(recipeIngredient: RecipeIngredient){
    Card( modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)
        .clickable { },
        elevation = 10.dp) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Box(modifier = Modifier.align(Alignment.Start)){
                Text(
                    text = (recipeIngredient.ingredient.name+" "+recipeIngredient.amount+" "+recipeIngredient.unit))
            }
            /*Box(modifier = Modifier
                .align(Alignment.End)
                .fillMaxHeight()) {
                IconButton(
                    onClick = { }) {
                    Icon(painter = painterResource(id = R.drawable.ic_baseline_close_24), contentDescription = "remove")
                }
            }*/
        }
    }
}