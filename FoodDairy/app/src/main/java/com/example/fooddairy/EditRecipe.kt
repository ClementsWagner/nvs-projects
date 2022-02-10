package com.example.fooddairy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddairy.databinding.ActivityEditRecipeBinding
import com.example.fooddairy.db.FoodDairyDatabase
import com.example.fooddairy.db.IngredientWithAmount
import com.example.fooddairy.db.RecipeRepository
import com.example.fooddairy.utils.RecipeIngredientAdapter
import com.example.fooddairy.viewModels.RecipeViewModel
import com.example.fooddairy.viewModels.ViewModelFactory

class EditRecipe : AppCompatActivity() {

    private lateinit var binding: ActivityEditRecipeBinding
    private lateinit var recipeViewModel: RecipeViewModel
    private lateinit var adapter: RecipeIngredientAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_recipe)
        val recipeDAO = FoodDairyDatabase.getInstance(this).recipeDao()
        val recipeRepository = RecipeRepository(recipeDAO)
        val factory = ViewModelFactory(recipeRepository)
        recipeViewModel = ViewModelProvider(this, factory).get(RecipeViewModel::class.java)
        binding.myViewModel = recipeViewModel
        binding.lifecycleOwner = this

        val recipeId: Int = intent.getIntExtra("recipe_id", 0)

        if(recipeId == 0){
            changeVisibility(View.GONE)
            recipeViewModel.initAddRecipe()
            binding.SaveOrAddRecipeBtn.setOnClickListener {
                if(recipeViewModel.recipeName.value.isNullOrEmpty()){
                    Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show()
                }
                else{
                    recipeViewModel.insertRecipe().observe(this,{
                        val intent = Intent(this, EditRecipe::class.java).apply {
                            putExtra("recipe_id", it.toInt())
                        }
                        finish()
                        startActivity(intent)
                    })
                }
            }
        }
        else{
            changeVisibility(View.VISIBLE)
            initRecycler(recipeId)
            recipeViewModel.initEditRecipe(recipeId)
            binding.SaveOrAddRecipeBtn.setOnClickListener {
                if(recipeViewModel.recipeName.value.isNullOrEmpty()) {
                    Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show()
                }
                else{
                    recipeViewModel.updateRecipe()
                    finish()
                }
            }
            binding.recipeIngredientFAB.setOnClickListener {
                val intent = Intent(this, AddRecipeIngredient::class.java).apply {
                    putExtra("recipe_id", recipeId)
                    putExtra("recipe_name", recipeViewModel.recipeName.value)
                }
                startActivity(intent)
            }
        }

        binding.CancelRecipeBtn.setOnClickListener { finish() }
    }

    private fun changeVisibility(visibility: Int){
        binding.recipeIngredientFAB.visibility = visibility
        binding.editRecipeIngredientList.visibility = visibility
        binding.editRecipeDescription.visibility = visibility
    }

    private fun initRecycler(recipeId: Int){
        binding.editRecipeIngredientList.layoutManager = LinearLayoutManager(this)
        adapter = RecipeIngredientAdapter(true) { ingredient: IngredientWithAmount, position: Int ->
            onDeleteItem(recipeId, ingredient, position)
        }
        binding.editRecipeIngredientList.adapter = adapter

        displayRecipeIngredients(recipeId)
    }

    private fun displayRecipeIngredients(recipeId: Int){
        recipeViewModel.getAllRecipeIngredients(recipeId).observe(this,{
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }


    private fun onDeleteItem(recipeId: Int, ingredientWithAmount: IngredientWithAmount, position: Int){
        recipeViewModel.deleteIngredientsFromRecipe(recipeId, ingredientWithAmount.ingredient.ingredientId)
        adapter.notifyItemRemoved(position)
    }

}