package com.example.fooddairy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddairy.databinding.IngredientAdapterLayoutBinding
import com.example.fooddairy.db.Ingredient

class IngredientsAdapter(private val clickListener: (Ingredient) -> Unit,
                         private val onDelete: (Ingredient) -> Unit) : RecyclerView.Adapter<IngredientsAdapter.MyViewHolder>()
{
    val ingredients = ArrayList<Ingredient?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: IngredientAdapterLayoutBinding = DataBindingUtil.inflate(layoutInflater, R.layout.ingredient_adapter_layout, parent, false)
        return MyViewHolder(binding);
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.bind(ingredients[position], clickListener, onDelete)
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }

    fun setList(ingredientList: List<Ingredient>){
        ingredients.clear();
        ingredients.addAll(ingredientList)
    }

    class MyViewHolder(val binding: IngredientAdapterLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(ingredient: Ingredient?,
                 clickListener: (Ingredient) -> Unit,
                 onDelete: (Ingredient) -> Unit) {
            if(ingredient!=null){
                binding.tvName.text = ingredient.name
                binding.tvCalories.text = ingredient.calories.toString()
                binding.listItemLayout.setOnClickListener{
                    clickListener(ingredient)
                }
                binding.deleteIngredient.setOnClickListener {
                    onDelete(ingredient)
                }
            }
        }

    }
}