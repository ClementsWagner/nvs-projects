package com.example.fooddairy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.fooddairy.databinding.ActivityAddRecipeIngredientBinding
import com.example.fooddairy.db.FoodDairyDatabase
import com.example.fooddairy.db.IngredientRepository
import com.example.fooddairy.db.RecipeIngredientRepository
import com.example.fooddairy.viewModels.RecipeIngredientViewModel
import com.example.fooddairy.viewModels.ViewModelFactory

class AddRecipeIngredient : AppCompatActivity() {

    private lateinit var binding: ActivityAddRecipeIngredientBinding
    private lateinit var recipeIngredientViewModel: RecipeIngredientViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val recipeId: Int = intent.getIntExtra("recipe_id",0)
        val recipeName: String = intent.getStringExtra("recipe_name")!!


        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_recipe_ingredient)
        val recipeIngredientDao = FoodDairyDatabase.getInstance(this).recipeIngredientDao()
        val ingredientDao = FoodDairyDatabase.getInstance(this).ingredientDao()
        val ingredientRepository = IngredientRepository(ingredientDao)
        val recipeIngredientRepository = RecipeIngredientRepository(recipeIngredientDao)
        val factory = ViewModelFactory(recipeIngredientRepository, ingredientRepository)
        recipeIngredientViewModel = ViewModelProvider(this, factory).get(RecipeIngredientViewModel::class.java)
        binding.myViewModel = recipeIngredientViewModel
        binding.lifecycleOwner = this

        recipeIngredientViewModel.initView(recipeId, recipeName)
    }
}