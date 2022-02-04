package com.example.fooddairy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddairy.databinding.IngredientAdapterLayoutBinding
import com.example.fooddairy.databinding.RecipeAdapterLayoutBinding
import com.example.fooddairy.db.Ingredient
import com.example.fooddairy.db.Recipe

class RecipeAdapter(private val clickListener: (Recipe) -> Unit) : RecyclerView.Adapter<RecipeAdapter.MyViewHolder>() {

    val recipes = ArrayList<Recipe?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: RecipeAdapterLayoutBinding = DataBindingUtil.inflate(layoutInflater, R.layout.recipe_adapter_layout, parent, false)
        return RecipeAdapter.MyViewHolder(binding);
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(recipes[position], clickListener)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    fun setList(recipeList: List<Recipe>){
        recipes.clear()
        recipes.addAll(recipeList)
        recipes.add(0, null)
    }

    class MyViewHolder(val binding: RecipeAdapterLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(recipe: Recipe?, clickListener: (Recipe) -> Unit){
            if(recipe != null){
                binding.tvRecipeName.text = recipe.name
                binding.recipeListItemLayout.setOnClickListener{
                    clickListener(recipe)
                }
            }
        }
    }
}