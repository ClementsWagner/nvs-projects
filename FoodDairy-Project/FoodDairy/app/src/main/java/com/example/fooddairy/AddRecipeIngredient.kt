package com.example.fooddairy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.SpinnerAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.fooddairy.databinding.ActivityAddRecipeIngredientBinding
import com.example.fooddairy.db.*
import com.example.fooddairy.db.model.Ingredient
import com.example.fooddairy.db.repositories.IngredientRepository
import com.example.fooddairy.db.repositories.RecipeIngredientRepository
import com.example.fooddairy.utils.IngredientSpinnerAdapter
import com.example.fooddairy.viewModels.RecipeIngredientViewModel
import com.example.fooddairy.viewModels.ViewModelFactory

class AddRecipeIngredient : AppCompatActivity() {

    private lateinit var binding: ActivityAddRecipeIngredientBinding
    private lateinit var recipeIngredientViewModel: RecipeIngredientViewModel
    private lateinit var adapter: IngredientSpinnerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pref = this.getSharedPreferences("datasource",0)
        var localStorage = pref.getBoolean("local_storage", true)

        val recipeId: Int = intent.getIntExtra("recipe_id",0)
        val recipeName: String = intent.getStringExtra("recipe_name")!!


        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_recipe_ingredient)
        val recipeIngredientDao = FoodDairyDatabase.getInstance(this).recipeIngredientDao()
        val ingredientDao = FoodDairyDatabase.getInstance(this).ingredientDao()
        val ingredientRepository = IngredientRepository(ingredientDao, localStorage)
        val recipeIngredientRepository = RecipeIngredientRepository(recipeIngredientDao, recipeId)
        val factory = ViewModelFactory(recipeIngredientRepository, ingredientRepository)
        recipeIngredientViewModel = ViewModelProvider(this, factory).get(RecipeIngredientViewModel::class.java)
        binding.myViewModel = recipeIngredientViewModel
        binding.lifecycleOwner = this

        recipeIngredientViewModel.getAllIngredients().observe(this,{
            recipeIngredientViewModel.ingredientsList.addAll(it)
            adapter.notifyDataSetChanged()
        })

        recipeIngredientViewModel.initView(recipeId, recipeName)
        adapter = IngredientSpinnerAdapter(this,
            recipeIngredientViewModel.ingredientsList)

        binding.addIngredientSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                recipeIngredientViewModel.setSelectedIngredient(adapter.getItem(position))
                binding.saveIngredientRecipe.isEnabled = true
            }

        }
        binding.addIngredientSpinner.adapter = adapter


        binding.cancelAddIngredient.setOnClickListener {
            finish()
        }

        binding.saveIngredientRecipe.setOnClickListener {
            if(recipeIngredientViewModel.amount.value.isNullOrEmpty()){
                Toast.makeText(this, "Please enter an amount", Toast.LENGTH_SHORT).show()
            }
            else{
                recipeIngredientViewModel.insertSelectedIngredientToRecipe()
                finish()
            }

        }
    }
}

