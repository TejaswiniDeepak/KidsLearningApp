package com.example.kidsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class QuestionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
       lateinit var mQuestionList:ArrayList<Question>
        lateinit var musername: String?
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)
        musername=intent.getStringExtra(Constants.USERNAME)
       mQuestionList= Constants.getQuestions()

    }
}