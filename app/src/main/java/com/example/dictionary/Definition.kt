package com.example.dictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class Definition : AppCompatActivity() {
    companion object {
        private const val KEY = "WORD_DEFINITION"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_definition)
        val backButton = findViewById<ImageView>(R.id.arrowBackImageView)
        val textArea = findViewById<TextView>(R.id.definitionTextView)

        textArea.text = intent.getStringExtra(KEY)

        backButton.setOnClickListener { finish() }
    }
}