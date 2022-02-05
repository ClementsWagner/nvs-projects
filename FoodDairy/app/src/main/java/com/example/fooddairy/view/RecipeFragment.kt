package com.example.fooddairy.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddairy.IngredientsAdapter
import com.example.fooddairy.R
import com.example.fooddairy.RecipeAdapter
import com.example.fooddairy.databinding.FragmentIngredientsBinding
import com.example.fooddairy.databinding.FragmentRecipeBinding
import com.example.fooddairy.db.FoodDairyDatabase
import com.example.fooddairy.db.Ingredient
import com.example.fooddairy.db.Recipe
import com.example.fooddairy.db.RecipeRepository
import com.example.fooddairy.viewModels.IngredientViewModel
import com.example.fooddairy.viewModels.RecipeViewModel
import com.example.fooddairy.viewModels.ViewModelFactory

class RecipeFragment : Fragment() {

    private lateinit var recipeViewModel: RecipeViewModel
    private lateinit var binding: FragmentRecipeBinding
    private lateinit var adapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeBinding.inflate(inflater, container, false)
        val dao = FoodDairyDatabase.getInstance(this.requireContext()).recipeDao()
        val repository = RecipeRepository(dao)
        val factory = ViewModelFactory(repository)
        recipeViewModel = ViewModelProvider(this, factory).get(RecipeViewModel::class.java)
        binding.myViewModel = recipeViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        initRecycler()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun initRecycler(){
        binding.recipeRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        adapter = RecipeAdapter({selectedItem: Recipe -> listItemClicked(selectedItem)})
        binding.recipeRecyclerView.adapter = adapter

        displayRecipes()
    }

    private fun displayRecipes(){
        recipeViewModel.getAllRecipes().observe(viewLifecycleOwner,{
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun listItemClicked(recipe: Recipe){

    }
}