package com.example.fooddairycompose.ui.bottomnav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fooddairycompose.R
import com.example.fooddairycompose.model.Ingredient
import com.example.fooddairycompose.model.Recipe
import com.example.fooddairycompose.ui.contentscreens.IngredientsContent
import com.example.fooddairycompose.ui.contentscreens.RecipesContent


@Composable
fun RecipeScreen(navController: NavController, recipes: List<Recipe>) {
    Column(
        modifier = Modifier
            .padding(bottom = 45.dp)
    ) {
        RecipesContent(navController = navController)
    }
}

@Composable
fun IngredientScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(bottom = 45.dp)
    ) {
        IngredientsContent(navController = navController)
    }
}