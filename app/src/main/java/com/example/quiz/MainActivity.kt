package com.example.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    lateinit var option1: Button
    lateinit var option2: Button
    lateinit var option3: Button
    lateinit var option4: Button
    lateinit var questionTextView: TextView
    var Score : Int =0
    var question: Int = 0
    lateinit var questions: List<Question>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
     fun wireWidgets(){
        option1 = findViewById(R.id.option_1)
        option2 = findViewById(R.id.option_2 )
        option3 = findViewById(R.id.option_3)
        option4 = findViewById(R.id.option_4)
        questionTextView = findViewById(R.id.Question)
     }
    fun nextQuestion(){
    var q: Question = questions[question]
        questionTextView.text = q.text
        option1.text = q.a1
        option2.text = q.a2
        option3.text = q.a3
        option4.text = q.a4

    }
    fun setQuestions() {
        val inputStream = resources.openRawResource(R.raw.questions)
        val jsonText = inputStream.bufferedReader().use { it.readText() }
        Log.d(TAG,"onCreate: $jsonText")
        val gson = Gson()
        val oType = object : TypeToken<List<Question>>(){} .type
        questions = gson.fromJson<List<Question>>(jsonText,oType)

        }
        }




