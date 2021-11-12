package com.example.demoactivitylifecyclelog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val text = intent.getStringExtra("text")
        findViewById<TextView>(R.id.textView).text = text
    }

    fun goBack(view: View){
        val textView = findViewById<TextView>(R.id.textView);
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("text", textView.text.toString())
        }
        view.context.startActivity(intent)
    }
}