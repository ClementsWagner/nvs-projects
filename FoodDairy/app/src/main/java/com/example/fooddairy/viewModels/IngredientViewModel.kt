package com.example.fooddairy.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddairy.db.model.Ingredient
import com.example.fooddairy.db.IngredientRepository
import kotlinx.coroutines.launch
import androidx.lifecycle.*
import kotlinx.coroutines.flow.collect

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
        fillEmptyWithDefault()
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
        fillEmptyWithDefault()
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

    private fun fillEmptyWithDefault(){
        inputCalories.value = if(inputCalories.value.isNullOrEmpty()) "0" else inputCalories.value
        fat.value = if(fat.value.isNullOrEmpty()) "0" else fat.value
        carbohydrates.value = if(carbohydrates.value.isNullOrEmpty()) "0" else carbohydrates.value
        sugar.value = if(sugar.value.isNullOrEmpty()) "0" else sugar.value
        protein.value = if(protein.value.isNullOrEmpty()) "0" else protein.value
        salt.value = if(salt.value.isNullOrEmpty()) "0" else salt.value
    }

    fun initDetails(ingredientId: Int) = viewModelScope.launch {
        ingredientToSaveOrAdd = ingredientRepository.getIngredientById(ingredientId)
        inputName.value = ingredientToSaveOrAdd.name
        inputCalories.value = ingredientToSaveOrAdd.calories.toString()
        nutritionFacts.value = ingredientToSaveOrAdd.getNutritionFactString()
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