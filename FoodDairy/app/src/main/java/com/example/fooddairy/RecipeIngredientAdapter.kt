package com.example.fooddairy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddairy.databinding.RecipeIngredientLayoutAdapterBinding
import com.example.fooddairy.db.IngredientWithAmount
import androidx.databinding.DataBindingUtil.inflate

class RecipeIngredientAdapter : RecyclerView.Adapter<RecipeIngredientAdapter.MyViewHolder>() {

    val recipeIngredients = ArrayList<IngredientWithAmount>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: RecipeIngredientLayoutAdapterBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.recipe_ingredient_layout_adapter, parent, false)
        return RecipeIngredientAdapter.MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(recipeIngredients[position])
    }

    override fun getItemCount(): Int {
        return recipeIngredients.size
    }

    fun setList(recipeIngredientList: List<IngredientWithAmount>){
        recipeIngredients.clear()
        recipeIngredients.addAll(recipeIngredientList)
    }

    class MyViewHolder(val binding: RecipeIngredientLayoutAdapterBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(ingredient: IngredientWithAmount){
            binding.recipeIngredientName.text = ingredient.ingredient.name
            binding.recipeIngredientAmount.text = ingredient.amount.toString()
        }

    }
}