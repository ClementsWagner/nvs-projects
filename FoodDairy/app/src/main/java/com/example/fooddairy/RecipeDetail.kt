package com.example.fooddairy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddairy.databinding.ActivityRecipeDetailBinding
import com.example.fooddairy.db.FoodDairyDatabase
import com.example.fooddairy.db.RecipeRepository
import com.example.fooddairy.viewModels.RecipeViewModel
import com.example.fooddairy.viewModels.ViewModelFactory

class RecipeDetail : AppCompatActivity() {

    private lateinit var adapter: RecipeIngredientAdapter
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

        initRecycler(recipeId)
        recipeViewModel.initDetails(recipeId)

    }

    private fun initRecycler(recipeId: Int){
        binding.recipeDetailIngredientList.layoutManager = LinearLayoutManager(this)
        adapter = RecipeIngredientAdapter()
        binding.recipeDetailIngredientList.adapter = adapter

        displayRecipeIngredients(recipeId)
    }

    private fun displayRecipeIngredients(recipeId: Int){
        recipeViewModel.getAllRecipeIngredients(recipeId).observe(this,{
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun onEditClick(recipeId: Int){
        val intent = Intent(this, EditRecipe::class.java).apply {
            putExtra("recipe_id", recipeId)
        }
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        recipeViewModel.initDetails(recipeId)
        super.onResume()
    }
}