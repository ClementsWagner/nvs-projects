package com.example.fooddairy.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Ingredient::class], version = 1)
abstract class FoodDairyDatabase : RoomDatabase() {
    abstract fun ingredientDao(): IngredientDAO

    companion object {
        @Volatile
        private var INSTANCE: FoodDairyDatabase? = null
        fun getInstance(context: Context): FoodDairyDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FoodDairyDatabase::class.java,
                        "fooddairy_database"
                    ).build()
                }
                return instance
            }
        }
    }



}