package com.example.fooddairy

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddairy.EditIngredient
import com.example.fooddairy.IngredientDetail
import com.example.fooddairy.utils.IngredientsAdapter
import com.example.fooddairy.viewModels.ViewModelFactory
import com.example.fooddairy.databinding.FragmentIngredientsBinding
import com.example.fooddairy.db.FoodDairyDatabase
import com.example.fooddairy.db.model.Ingredient
import com.example.fooddairy.db.repositories.IngredientRepository
import com.example.fooddairy.viewModels.IngredientViewModel

class IngredientsFragment : Fragment() {

    private lateinit var adapter: IngredientsAdapter
    private lateinit var ingredientViewModel: IngredientViewModel
    private lateinit var binding: FragmentIngredientsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val pref = this.requireContext().getSharedPreferences("datasource",0)
        var localStorage = pref.getBoolean("local_storage", true)

        binding = FragmentIngredientsBinding.inflate(inflater, container, false)
        val ingredientDao = FoodDairyDatabase.getInstance(this.requireContext()).ingredientDao()
        val ingredientRepository = IngredientRepository(ingredientDao, localStorage)
        val factory = ViewModelFactory(ingredientRepository)
        ingredientViewModel = ViewModelProvider(this, factory).get(IngredientViewModel::class.java)
        binding.myViewModel = ingredientViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.ingredientFAB.setOnClickListener {
            val intent = Intent(this.requireContext(), EditIngredient::class.java)
            startActivity(intent)
        }

        initRecycler()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun initRecycler(){
        binding.ingredientsRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        adapter = IngredientsAdapter({selectedItem: Ingredient -> listItemClicked(selectedItem)},
            { selectedItem: Ingredient, position: Int -> onDeleteItem(selectedItem, position)})
        binding.ingredientsRecyclerView.adapter = adapter

        displayIngredientsList()
    }

    private fun displayIngredientsList(){
        ingredientViewModel.getAllIngredients().observe(viewLifecycleOwner,{
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun onDeleteItem(ingredient: Ingredient, position: Int){
        ingredientViewModel.deleteIngredient(ingredient)
        adapter.notifyItemRemoved(position)
    }

    private fun listItemClicked(ingredient: Ingredient){
        val intent = Intent(this.requireContext(), IngredientDetail::class.java).apply {
            putExtra("ingredient_id", ingredient.ingredientId)
        }
        startActivity(intent)
    }
}