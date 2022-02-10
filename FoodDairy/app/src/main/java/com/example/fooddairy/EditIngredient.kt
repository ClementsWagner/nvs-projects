package com.example.fooddairy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.fooddairy.databinding.ActivityEditIngredientBinding
import com.example.fooddairy.db.FoodDairyDatabase
import com.example.fooddairy.db.repositories.IngredientRepository
import com.example.fooddairy.viewModels.IngredientViewModel
import com.example.fooddairy.viewModels.ViewModelFactory

class EditIngredient : AppCompatActivity() {
    private lateinit var binding: ActivityEditIngredientBinding
    private lateinit var ingredientViewModel: IngredientViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pref = this.getSharedPreferences("datasource",0)
        var localStorage = pref.getBoolean("local_storage", true)

        val ingredientId: Int = intent.getIntExtra("ingredient_id",0)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_ingredient)
        val dao = FoodDairyDatabase.getInstance(this).ingredientDao()
        val repository = IngredientRepository(dao, localStorage)
        val factory = ViewModelFactory(repository)
        ingredientViewModel = ViewModelProvider(this, factory).get(IngredientViewModel::class.java)
        binding.myViewModel = ingredientViewModel
        binding.lifecycleOwner = this

        if(ingredientId!=0){
            ingredientViewModel.initUpdate(ingredientId)
            binding.SaveOrAddIngredientButton.setOnClickListener{
                if(ingredientViewModel.inputName.value.isNullOrEmpty()){
                    Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show()
                }else if(ingredientViewModel.unit.value.isNullOrEmpty()){
                    Toast.makeText(this, "Please enter a unit", Toast.LENGTH_SHORT).show()
                }else{
                    ingredientViewModel.updateIngredient()
                    finish()
                }
            }
        }
        else{
            ingredientViewModel.initAdd()
            binding.SaveOrAddIngredientButton.setOnClickListener{
                if(ingredientViewModel.inputName.value.isNullOrEmpty()){
                    Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show()
                }else if(ingredientViewModel.unit.value.isNullOrEmpty()){
                    Toast.makeText(this, "Please enter a unit", Toast.LENGTH_SHORT).show()
                }else{
                    ingredientViewModel.insertIngredient()
                    finish()
                }
            }
        }

        binding.CancelIngrdientButton.setOnClickListener{finish()}

    }



}