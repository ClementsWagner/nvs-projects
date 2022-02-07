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
    val nutritionFacts = MutableLiveData<String>()

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

    fun deleteRecipe(recipe: Recipe) = viewModelScope.launch {
        recipeRepository.deleteRecipe(recipe)
    }

    fun updateNutritionFacts(ingredients: List<IngredientWithAmount>) = viewModelScope.launch {
        var calories: Int = 0
        var fat: Float = 0f
        var carbohydrate: Float = 0f
        var protein: Float = 0f
        var sugar: Float = 0f
        var salt: Float = 0f

        for (ingredient in ingredients){
            calories += (ingredient.ingredient.calories * (ingredient.amount/100)).toInt()
            fat += (ingredient.ingredient.fat)*(ingredient.amount/100)
            carbohydrate += (ingredient.ingredient.carbohydrates)*(ingredient.amount/100)
            protein += (ingredient.ingredient.protein)*(ingredient.amount/100)
            sugar += (ingredient.ingredient.sugar)*(ingredient.amount/100)
            salt += (ingredient.ingredient.salt)*(ingredient.amount/100)
        }
        var result: String = ""
        val pad: Int = 25
        result += ("Calories".padEnd(pad, ' ')+"$calories\n")
        result += ("Fat".padEnd(pad, ' ')+"$fat\n")
        result += ("Carbohydrates".padEnd(pad, ' ')+"$carbohydrate\n")
        result += ("Protein".padEnd(pad, ' ')+"$protein\n")
        result += ("Sugar".padEnd(pad, ' ')+"$sugar\n")
        result += ("Salt".padEnd(pad, ' ')+"$salt")

        nutritionFacts.value = result
    }
}