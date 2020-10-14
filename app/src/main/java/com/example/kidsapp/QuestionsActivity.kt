package com.example.kidsapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_questions.*

class QuestionsActivity : AppCompatActivity(),View.OnClickListener {
    private var mQuestionList:ArrayList<Question>?=null
    private var musername: String?=null
    private var index:Int=1
    private var mCorrectOption:Int=1
    private var mSelectedOption:Int=0
    private var QuestionCompleted:Boolean=false
    private var numberOfCorrectAnswers:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        musername=intent.getStringExtra(Constants.USERNAME)
       mQuestionList= Constants.getQuestions()
        Log.i("size","${mQuestionList!!.size}")

        option1.setOnClickListener(this)
        option2.setOnClickListener(this)
        option3.setOnClickListener(this)
        option4.setOnClickListener(this)
        btn_submit.setOnClickListener(this)
        setQuestion()


    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.option1 -> {
                originalView()
                optionClicked(option1, 1)
               // Toast.makeText(this, "option1 clicked", Toast.LENGTH_SHORT).show()
            }

            R.id.option2 -> {
                originalView()
                optionClicked(option2, 2)
           //     Toast.makeText(this, "option2 clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.option3 -> {
                originalView()
                optionClicked(option3, 3)
             //   Toast.makeText(this, "option3 clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.option4 -> {
                originalView()

                optionClicked(option4, 4)
               // Toast.makeText(this, "option4 clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.btn_submit -> {
                if (mSelectedOption == 0) {
                    originalView()
                    index++
                    if(index<=mQuestionList!!.size) {
                        setQuestion()
                        btn_submit.text="Submit"
                        Log.i("resu","$numberOfCorrectAnswers")
                    }
                    else{
                        var Result:Int=0
                        var intent=Intent(this,ResultActivity::class.java)
                        Constants.CORRECTANSWER=numberOfCorrectAnswers.toString()
                      // intent.putExtra(Constants.CORRECTANSWER,numberOfCorrectAnswers)
                        startActivity(intent)
                        finish()


                    }


                    /**   if (index < mQuestionList!!.size) {
                    index++
                    btn_submit.text = "Next"
                    setQuestion()
                    } else {
                    btn_submit.text = "Finish"

                    }

                     **/
                }
                    else {
                    if (mSelectedOption == mCorrectOption) {
                        correctAnswerView(mSelectedOption)
                        numberOfCorrectAnswers++
                        QuestionCompleted=true


                    } else {
                        wrongAnswerView(mSelectedOption)
                        correctAnswerView(mCorrectOption)
                        QuestionCompleted=true
                    }
                    mSelectedOption = 0
                    if(index==mQuestionList!!.size)
                    {
                        btn_submit.text="finish"
                    }
                    else
                    {
                        btn_submit.text="next"
                    }
                }



            }

        }
    }


    fun optionClicked(btn:Button,selectedOption:Int)
    {
      mSelectedOption=selectedOption

        btn.setBackgroundResource(R.drawable.btn_bg)
        btn.setTextColor(Color.parseColor("#6C9E9C"))
    }

    fun originalView()
    {

        option1.setBackgroundColor(Color.parseColor("#FFFFFF"))
        option1.setTextColor(Color.parseColor("#050A0A"))
        option2.setBackgroundColor(Color.parseColor("#FFFFFF"))
        option2.setTextColor(Color.parseColor("#050A0A"))
        option3.setBackgroundColor(Color.parseColor("#FFFFFF"))
        option3.setTextColor(Color.parseColor("#050A0A"))
        option4.setBackgroundColor(Color.parseColor("#FFFFFF"))
        option4.setTextColor(Color.parseColor("#050A0A"))
    }
    fun correctAnswerView(selectedOption: Int)
    {
when(selectedOption)
{
    1->option1.setBackgroundColor(Color.parseColor("#8CEC73"))
    2->option2.setBackgroundColor(Color.parseColor("#8CEC73"))
    3->option3.setBackgroundColor(Color.parseColor("#8CEC73"))
    4->option4.setBackgroundColor(Color.parseColor("#8CEC73"))
}
    }
    fun wrongAnswerView(selectedOption: Int)
    {
        when(selectedOption)
        {
            1->option1.setBackgroundColor(Color.parseColor("#E1516E"))
            2->option2.setBackgroundColor(Color.parseColor("#E1516E"))
            3->option3.setBackgroundColor(Color.parseColor("#E1516E"))
            4->option4.setBackgroundColor(Color.parseColor("#E1516E"))
        }
    }
    fun setQuestion()
    {
        var Question=mQuestionList!![index-1]
        animal_image.setImageResource(Question.image)
        option1.text= Question.optionOne
        option2.text= Question.optionTwo
        option3.text= Question.optionThree
        option4.text= Question.optionFour
        mCorrectOption=Question.correctAnswer

    }

}