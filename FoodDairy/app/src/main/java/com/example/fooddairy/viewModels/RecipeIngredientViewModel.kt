package com.example.fooddairy.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fooddairy.db.IngredientRepository
import com.example.fooddairy.db.RecipeIngredientRepository

class RecipeIngredientViewModel(private val recipeIngredientRepository: RecipeIngredientRepository, private val ingredientRepository: IngredientRepository) : ViewModel(){

    private var recipeId: Int = 0
    val recipeName = MutableLiveData<String>()

    init {
        recipeName.value = ""
    }

    fun initView(id: Int, name: String){
        recipeId = id
        recipeName.value = name
    }



    fun getAllIngredients(){

    }

    fun addIngredientToRecipe(){

    }
}