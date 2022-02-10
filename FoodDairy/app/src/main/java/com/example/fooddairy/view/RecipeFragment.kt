package com.example.fooddairy.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddairy.*
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
        binding.recipeFAB.setOnClickListener { onAdd() }
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun initRecycler(){
        binding.recipeRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        adapter = RecipeAdapter({selectedItem: Recipe -> listItemClicked(selectedItem)},
            { selectedItem: Recipe, position: Int -> onDeleteItem(selectedItem, position) })
        binding.recipeRecyclerView.adapter = adapter
        displayRecipes()
    }

    private fun displayRecipes(){
        recipeViewModel.getAllRecipes().observe(viewLifecycleOwner,{
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun onDeleteItem(recipe: Recipe, position: Int){
        recipeViewModel.deleteRecipe(recipe)
        adapter.notifyItemRemoved(position)
    }

    private fun listItemClicked(recipe: Recipe){
        val intent = Intent(this.requireContext(), RecipeDetail::class.java).apply {
            putExtra("recipe_id", recipe.recipeId)
        }
        startActivity(intent)
    }

    private fun onAdd(){
        val intent = Intent(this.requireContext(), EditRecipe::class.java)
        startActivity(intent)
    }
}