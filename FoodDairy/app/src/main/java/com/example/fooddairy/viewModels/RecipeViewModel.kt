package com.example.fooddairy.viewModels

import androidx.lifecycle.*
import android.transition.Visibility
import android.view.View
import com.example.fooddairy.RecipeIngredientAdapter
import com.example.fooddairy.db.IngredientWithAmount
import com.example.fooddairy.db.Recipe
import com.example.fooddairy.db.RecipeRepository
import com.example.fooddairy.db.RecipeWithIngredient
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RecipeViewModel(private val recipeRepository: RecipeRepository): ViewModel() {

    val saveOrAddButtonText = MutableLiveData<String>()
    private lateinit var recipeToSaveOrAdd: Recipe
    val recipeName= MutableLiveData<String>()
    val recipeDescription = MutableLiveData<String>()

    init {
        saveOrAddButtonText.value = "Add"
    }

    fun initEditRecipe(recipeId: Int) = viewModelScope.launch{
        saveOrAddButtonText.value = "Save"
        recipeToSaveOrAdd = recipeRepository.getRecipeById(recipeId)
        recipeName.value = recipeToSaveOrAdd.name
        recipeDescription.value = recipeToSaveOrAdd.description
    }

    fun initAddRecipe(){
        saveOrAddButtonText.value = "Add"
        recipeName.value = ""
        recipeDescription.value = ""
    }

    fun getAllRecipes() = liveData {
        recipeRepository.recipes.collect {
            emit(it)
        }
    }

    fun insertRecipe() = liveData {
        val recipeId = recipeRepository.insertRecipe(Recipe(0,recipeName.value!!, ""))
        emit(recipeId)
    }

    fun updateRecipe() = viewModelScope.launch {
        recipeToSaveOrAdd.name = recipeName.value!!
        recipeToSaveOrAdd.description = recipeDescription.value!!
        recipeRepository.updateRecipe(recipeToSaveOrAdd)
    }

    fun initDetails(recipeId: Int) = viewModelScope.launch {
        val recipe = recipeRepository.getRecipeDetailsById(recipeId)

        recipeName.value = recipe.recipe.name
        recipeDescription.value = recipe.recipe.description
    }

    fun getAllRecipeIngredients(recipeId: Int) = liveData{
        recipeRepository.getIngredientsWithAmount(recipeId).collect{
            emit(it)
        }
    }
}