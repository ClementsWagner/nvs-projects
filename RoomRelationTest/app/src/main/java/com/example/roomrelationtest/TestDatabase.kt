package com.example.roomrelationtest

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Recipe::class, Ingredient::class, RecipeIngredient::class], version = 1)
abstract class TestDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: TestDatabase? = null
        fun getInstance(context: Context): TestDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TestDatabase::class.java,
                        "test_database"
                    ).fallbackToDestructiveMigration().build()
                }
                return instance
            }
        }
    }



}