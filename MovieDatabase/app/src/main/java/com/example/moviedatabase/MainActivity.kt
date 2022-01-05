package com.example.moviedatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.lifecycle.lifecycleScope
import com.example.moviedatabase.model.Movie
import com.example.moviedatabase.model.ResponseAutoComplete
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.launch
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun onSearch(v : View){

        lifecycleScope.launch {
                val client = HttpClient(Android)
                val search = findViewById<EditText>(R.id.editTextTitle)
                val query: String = URLEncoder.encode(search.text.toString(), "utf-8")
                val response: HttpResponse = client.get("https://imdb8.p.rapidapi.com/auto-complete?q=$query"){
                method = HttpMethod.Get
                headers{
                    append("X-Rapidapi-Host", "imdb8.p.rapidapi.com")
                    append("X-Rapidapi-Key","aed1fb10c8msh7d1c523dcafe3f7p1c5e2djsnd992d882bdc3")
                }
            }
            val jsonResponse: String = response.receive()
            val movies = Gson().fromJson(jsonResponse, ResponseAutoComplete::class.java)
            populateSpinner(movies.movies)
        }
    }

    fun showDetails(v: View){
        val spinner = findViewById<Spinner>(R.id.spinnerTitle)
        val movie = spinner.selectedItem as Movie

        val intent = Intent(this, MovieDetails::class.java)
        intent.putExtra("MovieId",movie.id)
        startActivity(intent)
    }

    private fun populateSpinner(movies: ArrayList<Movie>){
        val spinner = findViewById<Spinner>(R.id.spinnerTitle)

        val adapter: ArrayAdapter<Movie> = ArrayAdapter(this,
            R.layout.support_simple_spinner_dropdown_item,
            movies)

        spinner.adapter = adapter
    }


}