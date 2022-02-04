package com.example.fooddairy.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Ingredient(

    @PrimaryKey(autoGenerate = true)
    //@ColumnInfo(name = "ingredient_id")
    var ingredientId: Int,

    @ColumnInfo(name = "ingredient_name")
    var name: String,

    @ColumnInfo(name = "ingredient_calories")
    var calories: Int)