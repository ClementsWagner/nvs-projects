package com.example.roomrelationtest

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Ingredient(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var calories: Int
)