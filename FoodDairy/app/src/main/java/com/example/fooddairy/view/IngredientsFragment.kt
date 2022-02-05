package com.example.fooddairy.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddairy.IngredientDetail
import com.example.fooddairy.IngredientsAdapter
import com.example.fooddairy.R
import com.example.fooddairy.viewModels.ViewModelFactory
import com.example.fooddairy.databinding.FragmentIngredientsBinding
import com.example.fooddairy.db.FoodDairyDatabase
import com.example.fooddairy.db.Ingredient
import com.example.fooddairy.db.IngredientRepository
import com.example.fooddairy.viewModels.IngredientViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [IngredientsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class IngredientsFragment : Fragment() {

    private lateinit var adapter: IngredientsAdapter
    private lateinit var ingredientViewModel: IngredientViewModel
    private lateinit var binding: FragmentIngredientsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIngredientsBinding.inflate(inflater, container, false)
        val ingredientDao = FoodDairyDatabase.getInstance(this.requireContext()).ingredientDao()
        val ingredientRepository = IngredientRepository(ingredientDao)
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
        adapter = IngredientsAdapter({selectedItem: Ingredient -> listItemClicked(selectedItem)})
        binding.ingredientsRecyclerView.adapter = adapter

        displayIngredientsList()
    }

    private fun displayIngredientsList(){
        ingredientViewModel.getAllIngredients().observe(viewLifecycleOwner,{
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun listItemClicked(ingredient: Ingredient){
        val intent = Intent(this.requireContext(), IngredientDetail::class.java).apply {
            putExtra("ingredient_id", ingredient.ingredientId)
        }
        startActivity(intent)
    }
}