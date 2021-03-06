package com.example.fooddairy

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddairy.databinding.FragmentRecipeBinding
import com.example.fooddairy.db.FoodDairyDatabase
import com.example.fooddairy.db.model.Recipe
import com.example.fooddairy.db.repositories.RecipeRepository
import com.example.fooddairy.utils.RecipeAdapter
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
        val pref = this.requireContext().getSharedPreferences("datasource",0)
        var localStorage = pref.getBoolean("local_storage", true)
        binding = FragmentRecipeBinding.inflate(inflater, container, false)
        val dao = FoodDairyDatabase.getInstance(this.requireContext()).recipeDao()
        val repository = RecipeRepository(dao)
        val factory = ViewModelFactory(repository)
        recipeViewModel = ViewModelProvider(this, factory).get(RecipeViewModel::class.java)
        binding.myViewModel = recipeViewModel
        binding.lifecycleOwner = viewLifecycleOwner


        binding.recipeFAB.setOnClickListener { onAdd() }
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initRecycler()
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