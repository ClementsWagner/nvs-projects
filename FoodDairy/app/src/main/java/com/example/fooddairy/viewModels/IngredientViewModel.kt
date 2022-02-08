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
    val nutritionFacts = MutableLiveData<String>()
    val unit = MutableLiveData<String>()
    val fat = MutableLiveData<String>()
    val carbohydrates = MutableLiveData<String>()
    val sugar = MutableLiveData<String>()
    val salt = MutableLiveData<String>()
    val protein = MutableLiveData<String>()

    init {
        saveOrAddButtonText.value = "Add"
        inputName.value = ""
        inputCalories.value = ""
        unit.value = ""
        fat.value = ""
        carbohydrates.value = ""
        sugar.value = ""
        salt.value = ""
        sugar.value = ""
        protein.value = ""
    }

    fun initAdd(){
        saveOrAddButtonText.value = "Add"
        inputName.value = ""
        inputCalories.value = ""
    }

    fun insertIngredient() = viewModelScope.launch {
        val newIngredient: Ingredient = Ingredient(0,
            inputName.value!!,
            inputCalories.value!!.toInt(),
            unit.value!!,
            fat.value!!.toFloat(),
            carbohydrates.value!!.toFloat(),
            sugar.value!!.toFloat(),
            protein.value!!.toFloat(),
            salt.value!!.toFloat())
        ingredientRepository.insertIngredient(newIngredient)
    }

    fun initUpdate(ingredientId: Int) = viewModelScope.launch {
        saveOrAddButtonText.value = "Save"
        add = false
        ingredientToSaveOrAdd = ingredientRepository.getIngredientById(ingredientId)
        inputName.value = ingredientToSaveOrAdd.name
        unit.value = ingredientToSaveOrAdd.unit
        inputCalories.value = ingredientToSaveOrAdd.calories.toString()
        fat.value = ingredientToSaveOrAdd.fat.toString()
        carbohydrates.value = ingredientToSaveOrAdd.carbohydrates.toString()
        sugar.value = ingredientToSaveOrAdd.sugar.toString()
        protein.value = ingredientToSaveOrAdd.protein.toString()
        salt.value = ingredientToSaveOrAdd.salt.toString()
    }

    fun updateIngredient() = viewModelScope.launch {
        ingredientToSaveOrAdd.name = inputName.value!!
        ingredientToSaveOrAdd.unit = unit.value!!
        ingredientToSaveOrAdd.calories = inputCalories.value!!.toInt()
        ingredientToSaveOrAdd.fat = fat.value!!.toFloat()
        ingredientToSaveOrAdd.carbohydrates = carbohydrates.value!!.toFloat()
        ingredientToSaveOrAdd.sugar = sugar.value!!.toFloat()
        ingredientToSaveOrAdd.protein = protein.value!!.toFloat()
        ingredientToSaveOrAdd.salt = salt.value!!.toFloat()
        ingredientRepository.updateIngredient(ingredientToSaveOrAdd)
    }

    fun initDetails(ingredientId: Int) = viewModelScope.launch {
        ingredientToSaveOrAdd = ingredientRepository.getIngredientById(ingredientId)
        inputName.value = ingredientToSaveOrAdd.name
        inputCalories.value = ingredientToSaveOrAdd.calories.toString()
        nutritionFacts.value = ingredientToSaveOrAdd.toString()
    }


    fun getAllIngredients() = liveData {
        ingredientRepository.ingredients.collect {
            emit(it)
        }
    }

    fun deleteIngredient(ingredient: Ingredient) = viewModelScope.launch {
        ingredientRepository.deleteIngredient(ingredient);
    }
}