package com.example.fooddairy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.example.fooddairy.view.IngredientsFragment
import com.example.fooddairy.view.RecipeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ingredientsFragment = IngredientsFragment()
        val recipeFragment = RecipeFragment()
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragmentContainerView, recipeFragment)
            addToBackStack(null)
            commit()
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener {
            if(savedInstanceState==null){
                when(it.itemId){
                    R.id.nav_ingredients ->setCurrentFragment(ingredientsFragment)
                    R.id.nav_recipes ->setCurrentFragment(recipeFragment)
                }
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView, fragment)
            addToBackStack(null)
            commit()
        }
    }
}