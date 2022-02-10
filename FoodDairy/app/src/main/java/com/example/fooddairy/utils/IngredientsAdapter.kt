package com.example.fooddairy.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddairy.R
import com.example.fooddairy.databinding.IngredientAdapterLayoutBinding
import com.example.fooddairy.db.Ingredient

class IngredientsAdapter(private val clickListener: (Ingredient) -> Unit,
                         private val onDelete: (Ingredient, Int) -> Unit) : RecyclerView.Adapter<IngredientsAdapter.MyViewHolder>()
{
    val ingredients = ArrayList<Ingredient?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: IngredientAdapterLayoutBinding = DataBindingUtil.inflate(layoutInflater,
            R.layout.ingredient_adapter_layout, parent, false)
        return MyViewHolder(binding);
    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.bind(ingredients[position], clickListener,
                onDelete = {ingredient: Ingredient,_: Int ->
                    ingredients.remove(ingredient)
                    onDelete(ingredient,position)})
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
                 onDelete: (Ingredient, Int) -> Unit) {
            if(ingredient!=null){
                binding.tvName.text = ingredient.name

                binding.ingredientListItem.setOnClickListener{
                    clickListener(ingredient)
                }
                binding.deleteIngredient.setOnClickListener {
                    onDelete(ingredient, this.adapterPosition)
                }
            }
        }

    }
}