package com.example.dictionary


/*

        DEVELOPED BY: GAUTAM CHANDRA SAHA
        DATE & TIME: 24/4/21 AT 9:07 PM
        DESCRIPTION: Dictionary Application

Key (Learner's):

f669cead-6701-4a3f-874d-0f8e6a5eafc1

Key (Intermediate Dictionary):

ea45ee7b-08dd-4177-8fad-fba3ad8cb4eb
*/

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    private lateinit var searchBtn: Button

    //    https://www.dictionaryapi.com/api/v3/references/learners/json/apple?key=your-api-key
    private lateinit var url: String
    private val apiKey = "f669cead-6701-4a3f-874d-0f8e6a5eafc1"
    private lateinit var word: EditText
    private lateinit var textOutput: TextView
    private lateinit var searchedWord: String
    private lateinit var jsonArray: JSONArray
    private lateinit var shortDefArray: JSONArray
    private lateinit var definition: Any

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        searchBtn = findViewById(R.id.searchButton)
        word = findViewById(R.id.searchEditText)
        textOutput = findViewById(R.id.textOutput)

        searchBtn.setOnClickListener {
            searchedWord = word.text.toString()

            //note: set the volley dependencies in the gradle file

            //update the url with the string queries
            url = "https://www.dictionaryapi.com/api/v3/references/learners/json/" +
                    "$searchedWord?" +
                    "key=$apiKey"

            //queue a new request
            //NOTE: give your app the internet permission in the manifest file
            val queueRequest = Volley.newRequestQueue(this)

            //get the response
            val jsonRequest = StringRequest(Request.Method.GET, url, { response ->
                try {
                    getDefinitionFromJSONArray(response)
                } catch (e: Exception) {
                    Toast.makeText(this, "Word not Found.", Toast.LENGTH_SHORT).show()
                }

            }, { error ->
                Toast.makeText(
                    this,
                    error.message + "\nCheck internet connection.",
                    Toast.LENGTH_SHORT
                ).show()
            })

            queueRequest.add(jsonRequest)
        }
    }

    private fun getDefinitionFromJSONArray(response: String?) {
        jsonArray = JSONArray(response)
        shortDefArray = jsonArray.getJSONObject(0).getJSONArray("shortdef")
        definition = shortDefArray.get(0)

        try {
            textOutput.text = definition.toString()
        } catch (e: java.lang.Exception) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }


        //intent to another activity
//        startActivity(Intent(this, Definition::class.java).putExtra(KEY, definition.toString()))
    }

    companion object {
        private const val KEY = "WORD_DEFINITION"
    }


}