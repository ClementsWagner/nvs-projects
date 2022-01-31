package com.example.fooddairy.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.fooddairy.R
import com.example.fooddairy.databinding.ActivityEditIngredientBinding
import com.example.fooddairy.databinding.ActivityIngredientDetailBinding
import com.example.fooddairy.db.FoodDairyDatabase
import com.example.fooddairy.db.IngredientDAO
import com.example.fooddairy.db.IngredientRepository
import com.example.fooddairy.viewModels.IngredientViewModel
import com.example.fooddairy.viewModels.ViewModelFactory

class EditIngredient : AppCompatActivity() {
    private lateinit var binding: ActivityEditIngredientBinding
    private lateinit var ingredientViewModel: IngredientViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ingredientId: Int = intent.getIntExtra("ingredient_id",0)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_ingredient)
        val dao = FoodDairyDatabase.getInstance(this).ingredientDao()
        val repository = IngredientRepository(dao)
        val factory = ViewModelFactory(repository)
        ingredientViewModel = ViewModelProvider(this, factory).get(IngredientViewModel::class.java)
        binding.myViewModel = ingredientViewModel

        if(ingredientId!=0){
            ingredientViewModel.initUpdate(ingredientId)
        }
    }
}