package com.example.fooddairycompose.ui.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fooddairycompose.model.Recipe

@Composable
fun RecipeList(navController: NavController, recipes: List<Recipe>){
    LazyColumn {
        items(recipes) { recipe ->  RecipeListItem(navController = navController, recipe = recipe)}
    }
}

@Composable
fun RecipeListItem(navController: NavController, recipe: Recipe){
    Card( modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)
        .clickable { navController.navigate("recipe_details/${recipe.id}")},
        elevation = 10.dp) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Text(text = recipe.name)
        }
    }
}