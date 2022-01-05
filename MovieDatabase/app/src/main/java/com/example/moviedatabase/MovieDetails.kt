package com.example.moviedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.moviedatabase.model.ResponseAutoComplete
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.launch
import java.net.URLEncoder

class MovieDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val movieId: String = intent.getStringExtra("MovieId").toString()

        lifecycleScope.launchWhenCreated {
            val client = HttpClient(Android)
            val search = findViewById<EditText>(R.id.editTextTitle)
            val response: HttpResponse =
                client.get("https://imdb8.p.rapidapi.com/title/get-details?tconst=$movieId") {
                    method = HttpMethod.Get
                    headers {
                        append("X-Rapidapi-Host", "imdb8.p.rapidapi.com")
                        append(
                            "X-Rapidapi-Key",
                            "aed1fb10c8msh7d1c523dcafe3f7p1c5e2djsnd992d882bdc3"
                        )
                    }
                }
            val jsonResponse: String = response.receive()
            //val details = Gson().fromJson(jsonResponse, MovieDetails::class.java)
            showDetails(jsonResponse)
        }
    }

    fun showDetails(details: String){
        val detailsView = findViewById<TextView>(R.id.textViewMovieDetails)

        detailsView.text = details.toString()
    }
}