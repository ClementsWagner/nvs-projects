package com.example.fooddairycompose.ui.contentscreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import com.example.fooddairycompose.R
import com.example.fooddairycompose.model.Ingredient
import com.example.fooddairycompose.repositories.IngredientRepository
import com.example.fooddairycompose.ui.components.EditTopAppBar
import com.example.fooddairycompose.ui.components.IngredientList
import com.example.fooddairycompose.ui.components.SaveOrCancel

private val ingredientRepository: IngredientRepository = IngredientRepository()

@Composable
fun IngredientsContent(navController: NavController){
    Scaffold(
        topBar = { TopAppBar(title = {Text("Ingredients")},backgroundColor = colorResource(id = R.color.teal_200))  },
        content = { IngredientList(navController = navController, ingredients = ingredientRepository.getAllIngredients())},
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = { FloatingActionButton(onClick = { navController.navigate("new_ingredient") }) {
            Icon(painter = painterResource(id = R.drawable.ic_baseline_add_24), contentDescription = "add")

        }}
    )
}

@Composable
fun IngredientDetails(navController: NavController, ingredientId: Int){
    val ingredient: Ingredient = ingredientRepository.getIngredientById(ingredientId)!!
    Scaffold(
        topBar = {
            EditTopAppBar(
                title = ingredient.name,
                onEdit = { navController.navigate("edit_ingredient/${ingredient.id}") },
                onBack = {navController.popBackStack()},
                onDelete = {/*Too do*/}
            ) },
        content = {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(text = "Calories: " + ingredient.calories.toString())
            }
        }
    )
}

@Composable
fun EditIngredient(navController: NavController, ingredientId: Int, new: Boolean){
    val ingredient: Ingredient = if(new) Ingredient(0,"",0) else ingredientRepository.getIngredientById(ingredientId)!!
    val name = remember { mutableStateOf(TextFieldValue(ingredient.name)) }
    val calories = remember { mutableStateOf(TextFieldValue(ingredient.calories.toString())) }
    Column(modifier = Modifier.padding(20.dp)) {
        TextField(
            value = name.value,
            label = { Text(text = "Name:")},
            onValueChange = {
                name.value=it
                ingredient.name=it.text})
        TextField(
            value = calories.value,
            label = { Text(text = "Calories:")},
            onValueChange = {
                if(it.text!="" && it.text.isDigitsOnly()){
                    calories.value = it
                    ingredient.calories=it.text.toInt()
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        SaveOrCancel(onSave = {
            if(new){
                ingredient.name = name.value.text
                ingredient.calories = calories.value.text.toInt()
                ingredientRepository.addIngredient(ingredient)
            }else{
                ingredientRepository.updateIngredient(ingredient)
            }
            navController.popBackStack()
        }, onCancel = {navController.popBackStack()})
    }
}



@Composable
fun NewIngredientScreen(navController: NavController, ingredient: Ingredient = Ingredient(name = "", calories = 0)){
    TextField(value = ingredient.name, onValueChange = {})
}