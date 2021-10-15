package com.example.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    lateinit var option1: Button
    lateinit var option2: Button
    lateinit var option3: Button
    lateinit var option4: Button
    lateinit var questionTextView: TextView
    lateinit var quiz: Quiz
    lateinit var questions: List<Question>
    lateinit var currQuestion: Question
    lateinit var mainGroup: Group
    lateinit var restart: Button
    lateinit var endgroup: Group
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wireWidgets()
        setQuestions()
        nextQuestion()
        endgroup.visibility = View.INVISIBLE
        option1.setOnClickListener {
            quiz.Score(0)
            // check if there are more questions and if so, set up the next question.
            nextQuestion()
        }
        option2.setOnClickListener {
            quiz.Score(1)
            nextQuestion()
        }
        option3.setOnClickListener {
            quiz.Score(2)
            nextQuestion()
        }
        option4.setOnClickListener {
            quiz.Score(3)
            nextQuestion()
        }

        restart.setOnClickListener {
            mainGroup.visibility = View.VISIBLE
            quiz.questionIndex = -1
            quiz.Score = 0
            nextQuestion()
            endgroup.visibility = View.INVISIBLE
        }
    }

    private fun setUpQuestionTextViews() {
        questionTextView.text = currQuestion.question
        option1.text = currQuestion.a1
        option2.text = currQuestion.a2
        option3.text = currQuestion.a3
        option4.text = currQuestion.a4
    }

    fun wireWidgets() {
        option1 = findViewById(R.id.option_1)
        option2 = findViewById(R.id.option_2)
        option3 = findViewById(R.id.option_3)
        option4 = findViewById(R.id.option_4)
        questionTextView = findViewById(R.id.Question)
        mainGroup = findViewById(R.id.main_group)
        restart = findViewById(R.id.restartbutton)
        endgroup = findViewById(R.id.endgroup)
    }

    fun nextQuestion() {
        if (quiz.hasmorequestions()) {
            currQuestion = quiz.getNextQuestion()
            setUpQuestionTextViews()
        } else {
            // if there aren't more questions, display final score, hide the other UI
            questionTextView.text = quiz.Score.toString()
            mainGroup.visibility = View.INVISIBLE
            endgroup.visibility = View.VISIBLE

        }
    }


    fun setQuestions() {
        val inputStream = resources.openRawResource(R.raw.questions)
        val jsonText = inputStream.bufferedReader().use { it.readText() }
        Log.d(TAG, "onCreate: $jsonText")
        val gson = Gson()
        val oType = object : TypeToken<List<Question>>() {}.type
        questions = gson.fromJson<List<Question>>(jsonText, oType)
        Log.d(TAG, "onCreate: $questions")
        // construct quiz object
        quiz = Quiz(questions)
    }

}
