package com.example.fooddairy.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddairy.db.Ingredient
import com.example.fooddairy.db.IngredientRepository
import kotlinx.coroutines.launch
import android.util.Patterns
import androidx.lifecycle.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class IngredientViewModel(private val ingredientRepository: IngredientRepository) : ViewModel() {

    fun getAllIngredients() = liveData {
        ingredientRepository.ingredients.collect {
            emit(it)
        }
    }

    fun deleteIngredients(ingredient: Ingredient) = viewModelScope.launch {
        ingredientRepository.deleteIngredient(ingredient);
    }

}