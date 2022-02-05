package com.example.fooddairy.viewModels

import androidx.lifecycle.*
import com.example.fooddairy.RecipeIngredientAdapter
import com.example.fooddairy.db.IngredientWithAmount
import com.example.fooddairy.db.Recipe
import com.example.fooddairy.db.RecipeRepository
import com.example.fooddairy.db.RecipeWithIngredient
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RecipeViewModel(private val recipeRepository: RecipeRepository): ViewModel() {

    private var add: Boolean = true
    val saveOrAddButtonText = MutableLiveData<String>()
    private lateinit var recipeToSaveOrAdd: Recipe
//    private val ingredientsToSaveOrAdd: LiveData<List<IngredientWithAmount>>()
    val recipeName= MutableLiveData<String>()
    val recipeDescription = MutableLiveData<String>()

    init {
        saveOrAddButtonText.value = "Add"
    }

    fun initEditRecipe(recipeId: Int) = viewModelScope.launch{
        add = false
        saveOrAddButtonText.value = "Save"
        recipeToSaveOrAdd = recipeRepository.getRecipeById(recipeId)
        recipeName.value = recipeToSaveOrAdd.name
        recipeDescription.value = recipeToSaveOrAdd.description
    }

    fun initAddRecipe(){
        add = true
        saveOrAddButtonText.value = "Add"
        recipeName.value = ""
        recipeDescription.value = ""
    }

    fun getAllRecipes() = liveData {
        recipeRepository.recipes.collect {
            emit(it)
        }
    }

    fun insertRecipe() = viewModelScope.launch {
        //recipeRepository.insertRecipe(newRecipe)
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