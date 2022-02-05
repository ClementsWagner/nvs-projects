package com.example.fooddairy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.fooddairy.databinding.ActivityRecipeDetailBinding
import com.example.fooddairy.db.FoodDairyDatabase
import com.example.fooddairy.db.RecipeRepository
import com.example.fooddairy.viewModels.RecipeViewModel
import com.example.fooddairy.viewModels.ViewModelFactory

class RecipeDetail : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeDetailBinding
    private lateinit var recipeViewModel: RecipeViewModel
    private var recipeId: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // showing the back button in action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe_detail)
        val dao = FoodDairyDatabase.getInstance(this).recipeDao()
        val repository = RecipeRepository(dao)
        val factory = ViewModelFactory(repository)
        recipeViewModel = ViewModelProvider(this, factory).get(RecipeViewModel::class.java)
        binding.myViewModel = recipeViewModel
        binding.lifecycleOwner = this

        recipeId = intent.getIntExtra("recipe_id", 0)

        binding.recipeEditBtn.setOnClickListener { onEditClick(recipeId) }

        recipeViewModel.initDetails(recipeId)
    }


    private fun onEditClick(recipeId: Int){

    }
}