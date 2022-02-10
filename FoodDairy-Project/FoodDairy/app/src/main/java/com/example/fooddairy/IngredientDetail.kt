package com.example.fooddairy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.fooddairy.databinding.ActivityIngredientDetailBinding
import com.example.fooddairy.db.FoodDairyDatabase
import com.example.fooddairy.db.repositories.IngredientRepository
import com.example.fooddairy.viewModels.IngredientViewModel
import com.example.fooddairy.viewModels.ViewModelFactory

class IngredientDetail : AppCompatActivity() {
    private lateinit var binding: ActivityIngredientDetailBinding
    private lateinit var ingredientViewModel: IngredientViewModel
    private var ingredientId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pref = this.getSharedPreferences("datasource",0)
        var localStorage = pref.getBoolean("local_storage", true)
        // showing the back button in action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_ingredient_detail)
        val dao = FoodDairyDatabase.getInstance(this).ingredientDao()
        val repository = IngredientRepository(dao, localStorage)
        val factory = ViewModelFactory(repository)
        ingredientViewModel = ViewModelProvider(this, factory).get(IngredientViewModel::class.java)
        binding.myViewModel = ingredientViewModel
        binding.lifecycleOwner = this

        ingredientId  = intent.getIntExtra("ingredient_id",0)

        binding.EditIngredientButton.setOnClickListener { onEditClick(ingredientId) }

    }

    override fun onResume() {
        ingredientViewModel.initDetails(ingredientId)
        super.onResume()
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

    private fun onEditClick(ingredientId: Int){
        val intent = Intent(this, EditIngredient::class.java).apply {
            putExtra("ingredient_id", ingredientId)
        }
        startActivity(intent)
    }
}