package com.example.kidsapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_questions.*

class QuestionsActivity : AppCompatActivity() {
    private var mQuestionList:ArrayList<Question>?=null
    private var musername: String?=null
    private var index:Int=1
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        musername=intent.getStringExtra(Constants.USERNAME)
       mQuestionList= Constants.getQuestions()

        option1.setOnClickListener(this)


        animal_image.setImageResource(R.drawable.tiger)
        option1.text= mQuestionList!![index - 1].optionOne
        option2.text= mQuestionList!![index - 1].optionTwo
        option3.text= mQuestionList!![index - 1].optionThree
        option4.text= mQuestionList!![index - 1].optionFour



    }
    fun onClick(v: View?) {
        when (v?.id) {
            R.id.option1 -> {
                Toast.makeText(this, "one one selected", Toast.LENGTH_SHORT).show()
            }
            R.id.option2 -> {
                Toast.makeText(this, "one one selected", Toast.LENGTH_SHORT).show()
            }
        }
    }
}