package com.example.fooddairy.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fooddairy.db.IngredientRepository
import java.lang.IllegalArgumentException

class ViewModelFactory(
    private val repository: IngredientRepository
    ): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(IngredientViewModel::class.java)){
            return IngredientViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}