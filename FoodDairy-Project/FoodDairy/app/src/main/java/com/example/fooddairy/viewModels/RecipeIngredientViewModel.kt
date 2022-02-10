package com.example.fooddairy.viewModels

import androidx.lifecycle.*
import com.example.fooddairy.db.model.Ingredient
import com.example.fooddairy.db.model.RecipeIngredient
import com.example.fooddairy.db.repositories.IngredientRepository
import com.example.fooddairy.db.repositories.RecipeIngredientRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RecipeIngredientViewModel(private val recipeIngredientRepository: RecipeIngredientRepository, private val ingredientRepository: IngredientRepository) : ViewModel(){

    private var recipeId: Int = 0
    val recipeName = MutableLiveData<String>()
    val ingredientUnit = MutableLiveData<String>()
    val amount = MutableLiveData<String>()
    val ingredientsList: MutableList<Ingredient> = mutableListOf()
    private lateinit var selectedIngredient: Ingredient

    init {
        recipeName.value = ""
        ingredientUnit.value = ""
    }

    fun initView(id: Int, name: String){
        recipeId = id
        recipeName.value = name
    }

    fun setSelectedIngredient(ingredient: Ingredient) {
        selectedIngredient = ingredient
        ingredientUnit.value = selectedIngredient.unit
    }

    fun getAllIngredients() = liveData {
        ingredientRepository.ingredients.collect {
            emit(it)
        }
    }

    fun insertSelectedIngredientToRecipe() = viewModelScope.launch {
        recipeIngredientRepository.insertRecipeIngredient(
            RecipeIngredient(
                recipeId,
                selectedIngredient.ingredientId,
                amount.value!!.toFloat())
        )
    }
}