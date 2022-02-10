package com.example.fooddairy.db.model

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
    var calories: Int = 0,
    var unit: String = "",
    var fat: Float = 0f,
    var carbohydrates: Float = 0f,
    var sugar: Float = 0f,
    var protein: Float = 0f,
    var salt: Float = 0f){

    fun getNutritionFactString(): String{
        var result: String = ""
        val pad: Int = 30
        result += "Nutrition facts per 100 $unit:\n"
        result += ("Calories".padEnd(pad, ' ')+"$calories\n")
        result += ("Fat".padEnd(pad, ' ')+"$fat\n")
        result += ("Carbohydrates".padEnd(pad, ' ')+"$carbohydrates\n")
        result += ("Protein".padEnd(pad, ' ')+"$protein\n")
        result += ("Sugar".padEnd(pad, ' ')+"$sugar\n")
        result += ("Salt".padEnd(pad, ' ')+"$salt")
        return result
    }

    override fun toString(): String {
        return name
    }
}