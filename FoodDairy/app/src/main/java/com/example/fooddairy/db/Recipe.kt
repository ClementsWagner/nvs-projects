package com.example.fooddairy.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Recipe")
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "recipe_id")
    var id: Int,

    @ColumnInfo(name = "recipe_name")
    var name: String,

    @ColumnInfo(name = "recipe_description")
    var description: String
    )
