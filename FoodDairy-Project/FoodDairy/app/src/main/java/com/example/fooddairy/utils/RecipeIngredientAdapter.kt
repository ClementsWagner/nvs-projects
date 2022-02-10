package com.example.fooddairy.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddairy.databinding.RecipeIngredientLayoutAdapterBinding
import com.example.fooddairy.db.model.IngredientWithAmount
import com.example.fooddairy.R

class RecipeIngredientAdapter(
    private val deletable: Boolean = false,
    private val onDelete: (IngredientWithAmount, Int) -> Unit = { _: IngredientWithAmount, _:Int ->}) : RecyclerView.Adapter<RecipeIngredientAdapter.MyViewHolder>() {

    val recipeIngredients = ArrayList<IngredientWithAmount>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: RecipeIngredientLayoutAdapterBinding =
            DataBindingUtil.inflate(layoutInflater,
                R.layout.recipe_ingredient_layout_adapter, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(recipeIngredients[position], deletable, onDelete)
    }

    override fun getItemCount(): Int {
        return recipeIngredients.size
    }

    fun setList(recipeIngredientList: List<IngredientWithAmount>){
        recipeIngredients.clear()
        recipeIngredients.addAll(recipeIngredientList)
    }

    class MyViewHolder(val binding: RecipeIngredientLayoutAdapterBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(ingredient: IngredientWithAmount,
                 deletable: Boolean = false,
                 onDelete: (IngredientWithAmount, Int) -> Unit = { _: IngredientWithAmount, _:Int ->}){
            binding.recipeIngredientName.text = ingredient.ingredient.name
            binding.recipeIngredientAmount.text = ingredient.amount.toString() + ingredient.ingredient.unit

            if(deletable){
                binding.deleteRecipeIngredient.visibility = View.VISIBLE
                binding.deleteRecipeIngredient.setOnClickListener {
                    onDelete(ingredient, this.adapterPosition)
                }
            }else{
                binding.deleteRecipeIngredient.visibility = View.GONE
            }

        }

    }
}