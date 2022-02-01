package com.example.fooddairy.db

import androidx.room.*

@Entity(primaryKeys = ["recipe_id", "ingredient_id"])
public class RecipeIngredient(
    @ColumnInfo(name = "recipe_id")
    var recipeId: Int,

    @ColumnInfo(name = "ingredient_id")
    var ingredientId: Int,

    @ColumnInfo(name = "amount")
    var amount: Float
)

public class RecipeWithIngredient(
    @Embedded val recipe: Recipe,
    @Relation(
        parentColumn = "recipe_id",
        entityColumn = "ingredient_id",
        associateBy = Junction(RecipeIngredient::class)
    )
    val ingredients: List<Ingredient>,

    @Relation(
        parentColumn = "recipe_id",
        entityColumn = "ingredient_id"
    )
    val recipeIngredients: List<RecipeIngredient>
)
