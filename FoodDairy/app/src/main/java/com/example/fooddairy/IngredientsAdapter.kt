package com.example.fooddairy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddairy.model.Ingredient

class IngredientsAdapter(private val ingredients: ArrayList<Ingredient>) : RecyclerView.Adapter<IngredientsAdapter.ViewHolderItem>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    : IngredientsAdapter.ViewHolderItem {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ingredient_adapter_layout, parent, false)
        return ViewHolderItem(view)
    }

    override fun onBindViewHolder(holder: IngredientsAdapter.ViewHolderItem, position: Int) {
        holder.name?.text = ingredients[position].name
        holder.calories?.text = ingredients[position].calories.toString()
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }

    class ViewHolderItem(v: View) : RecyclerView.ViewHolder(v) {
        val name = v.findViewById<TextView>(R.id.tvName)
        val calories = v.findViewById<TextView>(R.id.tvCalories)
    }
}