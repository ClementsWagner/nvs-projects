package com.example.fooddairy.db

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.fooddairy.db.model.Ingredient
import com.example.fooddairy.db.model.Recipe
import com.example.fooddairy.db.model.RecipeIngredient

data class RecipeWithIngredient(
    @Embedded val recipe: Recipe,
    @Relation(
        parentColumn = "recipeId",
        entityColumn = "ingredientId",
        associateBy = Junction(RecipeIngredient::class)
    )
    val ingredients: List<Ingredient>,

    @Relation(
        parentColumn = "recipeId",
        entityColumn = "ingredientId"
    )
    val recipeIngredients: List<RecipeIngredient>
)
/*


@Relation(
        parentColumn = "recipe_id",
        entityColumn = "ingredient_fid"
    )
    val recipeIngredients: List<RecipeIngredient>

 */