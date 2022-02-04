package com.example.roomrelationtest

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var description: String
)