package com.example.fooddairy.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fooddairy.db.IngredientRepository
import com.example.fooddairy.db.RecipeRepository
import java.lang.IllegalArgumentException

class ViewModelFactory<R>(
    private val repository: R
    ): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(IngredientViewModel::class.java)){
            return IngredientViewModel(repository as IngredientRepository) as T
        }
        if(modelClass.isAssignableFrom(RecipeViewModel::class.java)){
            return RecipeViewModel(repository as RecipeRepository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}