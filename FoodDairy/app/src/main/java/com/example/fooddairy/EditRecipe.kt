package com.example.fooddairy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddairy.databinding.ActivityEditRecipeBinding
import com.example.fooddairy.databinding.ActivityRecipeDetailBinding
import com.example.fooddairy.db.FoodDairyDatabase
import com.example.fooddairy.db.RecipeRepository
import com.example.fooddairy.viewModels.RecipeViewModel
import com.example.fooddairy.viewModels.ViewModelFactory

class EditRecipe : AppCompatActivity() {

    private lateinit var binding: ActivityEditRecipeBinding
    private lateinit var recipeViewModel: RecipeViewModel
    private lateinit var adapter: RecipeIngredientAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_recipe)
        val dao = FoodDairyDatabase.getInstance(this).recipeDao()
        val repository = RecipeRepository(dao)
        val factory = ViewModelFactory(repository)
        recipeViewModel = ViewModelProvider(this, factory).get(RecipeViewModel::class.java)
        binding.myViewModel = recipeViewModel
        binding.lifecycleOwner = this

        val recipeId: Int = intent.getIntExtra("recipe_id", 0)

        if(recipeId == 0){
            recipeViewModel.initAddRecipe()
            binding.SaveOrAddRecipeBtn.setOnClickListener {
                recipeViewModel.insertRecipe()
                finish()
            }
        }
        else{
            recipeViewModel.initEditRecipe(recipeId)
            binding.SaveOrAddRecipeBtn.setOnClickListener {
                recipeViewModel.updateRecipe()
                finish()
            }
        }

        binding.CancelRecipeBtn.setOnClickListener { finish() }

        initRecycler(recipeId)
    }

    private fun initRecycler(recipeId: Int){
        binding.editRecipeIngredientList.layoutManager = LinearLayoutManager(this)
        adapter = RecipeIngredientAdapter()
        binding.editRecipeIngredientList.adapter = adapter

        displayRecipeIngredients(recipeId)
    }

    private fun displayRecipeIngredients(recipeId: Int){
        recipeViewModel.getAllRecipeIngredients(recipeId).observe(this,{
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }



}