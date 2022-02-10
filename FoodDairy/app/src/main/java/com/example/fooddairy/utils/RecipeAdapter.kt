package com.example.fooddairy.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddairy.R
import com.example.fooddairy.databinding.RecipeAdapterLayoutBinding
import com.example.fooddairy.db.Recipe

class RecipeAdapter(private val clickListener: (Recipe) -> Unit,
                    private val onDelete: (Recipe, Int) -> Unit) : RecyclerView.Adapter<RecipeAdapter.MyViewHolder>() {

    val recipes = ArrayList<Recipe?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: RecipeAdapterLayoutBinding = DataBindingUtil.inflate(layoutInflater,
            R.layout.recipe_adapter_layout, parent, false)
        return MyViewHolder(binding);
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(recipes[position], clickListener, onDelete)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    fun setList(recipeList: List<Recipe>){
        recipes.clear()
        recipes.addAll(recipeList)
    }

    class MyViewHolder(val binding: RecipeAdapterLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(recipe: Recipe?,
                 clickListener: (Recipe) -> Unit,
                 onDelete: (Recipe, Int) -> Unit){
            if(recipe != null){
                binding.tvRecipeName.text = recipe.name

                binding.recipeListItem.setOnClickListener{
                    clickListener(recipe)
                }
                binding.deleteRecipe.setOnClickListener {
                    onDelete(recipe, this.adapterPosition)
                }
            }
        }
    }
}