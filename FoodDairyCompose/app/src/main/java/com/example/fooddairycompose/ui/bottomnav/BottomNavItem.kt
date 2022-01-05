package com.example.fooddairycompose.ui.bottomnav

import androidx.compose.material.Icon
import com.example.fooddairycompose.R

sealed class BottomNavItem (var title:String, var icon:Int, var screen_route:String){
     object Recipes : BottomNavItem("Recipes", R.drawable.ic_baseline_food_bank_24, "recipe")
     object Ingredients : BottomNavItem("Ingredients", R.drawable.ic_baseline_fastfood_24, "ingredients")
}