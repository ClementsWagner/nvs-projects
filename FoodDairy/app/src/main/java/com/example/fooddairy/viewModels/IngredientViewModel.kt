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

    private var add: Boolean = true
    private lateinit var ingredientToSaveOrAdd: Ingredient
    val saveOrAddButtonText = MutableLiveData<String>()
    val inputName = MutableLiveData<String>()
    val inputCalories = MutableLiveData<String>()

    init {
        saveOrAddButtonText.value = "Add"
        //inputName.value = ""
        //inputCalories.value = ""
    }

    fun initAdd(){
        saveOrAddButtonText.value = "Add"
        inputName.value = ""
        inputCalories.value = ""
    }

    fun insertIngredient() = viewModelScope.launch {
        val newIngredient: Ingredient = Ingredient(0,inputName.value!!, inputCalories.value!!.toInt())
        ingredientRepository.insertIngredient(newIngredient)
    }

    fun initUpdate(ingredientId: Int) = viewModelScope.launch {
        saveOrAddButtonText.value = "Save"
        add = false
        ingredientToSaveOrAdd = ingredientRepository.getIngredientById(ingredientId)
        inputName.value = ingredientToSaveOrAdd.name
        inputCalories.value = ingredientToSaveOrAdd.calories.toString()
    }

    fun updateIngredient() = viewModelScope.launch {
        ingredientToSaveOrAdd.name = inputName.value!!
        ingredientToSaveOrAdd.calories = inputCalories.value!!.toInt()
        ingredientRepository.updateIngredient(ingredientToSaveOrAdd)
    }

    fun initDetails(ingredientId: Int) = viewModelScope.launch {
        ingredientToSaveOrAdd = ingredientRepository.getIngredientById(ingredientId)
        inputName.value = ingredientToSaveOrAdd.name
        inputCalories.value = ingredientToSaveOrAdd.calories.toString()
    }


    fun getAllIngredients() = liveData {
        ingredientRepository.ingredients.collect {
            emit(it)
        }
    }

    fun deleteIngredients(ingredient: Ingredient) = viewModelScope.launch {
        ingredientRepository.deleteIngredient(ingredient);
    }
}