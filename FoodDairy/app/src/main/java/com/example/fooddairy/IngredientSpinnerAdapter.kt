package com.example.fooddairy

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.fooddairy.databinding.IngredientSpinnerItemLayoutBinding
import com.example.fooddairy.db.Ingredient
import com.example.fooddairy.db.IngredientWithAmount

class IngredientSpinnerAdapter(private val sourceContext: Context,
                               private val ingredients: MutableList<Ingredient>,
                               private val onSelect: (Ingredient) -> Unit
): ArrayAdapter<Ingredient>(sourceContext, 0, ingredients) {


    override fun getCount(): Int {
        return ingredients.size
    }

    override fun getItem(position: Int): Ingredient {
        return ingredients[position]
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }


    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
        val ingredient = getItem(position)

        val view = convertView ?: LayoutInflater.from(sourceContext).inflate(
            R.layout.ingredient_spinner_item_layout,
            parent,
            false
        )

        view.findViewById<TextView>(R.id.ingredientSpinnerItem).text = ingredient.name
        view.setOnClickListener {
            onSelect(ingredient)
        }

        return view
    }

}