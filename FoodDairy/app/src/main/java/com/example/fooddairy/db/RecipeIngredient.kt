package com.example.fooddairy.db

import androidx.room.*

@Entity(primaryKeys = ["recipeId", "ingredientId"],
    /*foreignKeys = [ForeignKey(entity = Recipe::class,
        parentColumns = ["recipe_id"],
        childColumns = ["recipe_fid"]), ForeignKey(entity = Ingredient::class,
        parentColumns = ["ingredient_id"],
        childColumns = ["ingredient_fid"])]*/
)

data class RecipeIngredient(
    //@ColumnInfo(name = "recipe_fid")
    var recipeId: Int,

    //@ColumnInfo(name = "ingredient_fid")
    var ingredientId: Int,

    //@ColumnInfo(name = "amount")
    var amount: Float
)
