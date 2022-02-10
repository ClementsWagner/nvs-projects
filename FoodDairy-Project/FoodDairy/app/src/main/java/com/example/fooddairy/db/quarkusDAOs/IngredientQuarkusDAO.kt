package com.example.fooddairy.db.quarkusDAOs

import com.example.fooddairy.db.model.Ingredient
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class IngredientQuarkusDAO{

    var client: HttpClient = HttpClient(CIO)

    public fun getAllIngredients(): Flow<List<Ingredient>> = flow {
        val response: HttpResponse = client.get("https//localhost:8080/Ingredient")
        print(response)
    }
}