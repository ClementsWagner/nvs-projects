package com.example.fooddairy.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.fooddairy.db.Recipe
import com.example.fooddairy.db.RecipeRepository
import com.example.fooddairy.db.RecipeWithIngredient
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RecipeViewModel(private val recipeRepository: RecipeRepository): ViewModel() {

    fun getAllRecipes() = liveData {
        recipeRepository.recipes.collect {
            emit(it)
        }
    }

    fun insertRecipe(newRecipe: Recipe) = viewModelScope.launch {
        recipeRepository.insertRecipe(newRecipe)
    }
}