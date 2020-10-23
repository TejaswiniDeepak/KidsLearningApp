package com.example.kidsapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_questions.*
import java.util.*
import kotlin.collections.ArrayList


class QuestionsActivity : AppCompatActivity(),View.OnClickListener, TextToSpeech.OnInitListener{
    private var mQuestionList: Question? =null
    private var musername: String?=null
    private var index:Int=1
    private var mCorrectOption:Int=1
    private var mSelectedOption:Int=0
    private var QuestionCompleted:Boolean=false
    private var numberOfCorrectAnswers:Int=0
    private var tts:TextToSpeech?=null
    private var questionNumber:Int=1

    override fun onCreate(savedInstanceState: Bundle?) {


        val dbHandler= Database(this, null)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        musername=intent.getStringExtra(Constants.USERNAME)
       mQuestionList= dbHandler.getQuestions(questionNumber)
   //Constants.TOTALQUESTIONS=dbHandler.getProfilesCount()
        tts= TextToSpeech(this,this)

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
                    if (index <Constants.TOTALQUESTIONS) {
                        setQuestion()
                        btn_submit.text = "Submit"
                        Log.i("resu", "$numberOfCorrectAnswers")
                    } else {
                        var Result = 0.0
                        var intent = Intent(this, ResultActivity::class.java)
                        Constants.CORRECTANSWER = numberOfCorrectAnswers
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
                } else {
                    if (mSelectedOption == mCorrectOption) {
                        tts!!.speak("correct",TextToSpeech.QUEUE_FLUSH,null,"")
                        correctAnswerView(mSelectedOption)
                        numberOfCorrectAnswers++
                        QuestionCompleted = true


                    } else {
                        wrongAnswerView(mSelectedOption)
                        tts!!.speak("wrong",TextToSpeech.QUEUE_FLUSH,null,"")
                        correctAnswerView(mCorrectOption)
                        QuestionCompleted = true
                    }
                    mSelectedOption = 0
                    if (index == Constants.TOTALQUESTIONS-1) {
                        btn_submit.text = "finish"
                    } else {
                        btn_submit.text = "next"
                    }
                }


            }

        }
    }


    fun optionClicked(btn: Button, selectedOption: Int)
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
        //tts!!.speak("correct",TextToSpeech.QUEUE_ADD,null,"")
when(selectedOption)
{
    1 -> option1.setBackgroundColor(Color.parseColor("#8CEC73"))
    2 -> option2.setBackgroundColor(Color.parseColor("#8CEC73"))
    3 -> option3.setBackgroundColor(Color.parseColor("#8CEC73"))
    4 -> option4.setBackgroundColor(Color.parseColor("#8CEC73"))
}
    }
    fun wrongAnswerView(selectedOption: Int)
    {

        when(selectedOption)
        {
            1 -> option1.setBackgroundColor(Color.parseColor("#E1516E"))
            2 -> option2.setBackgroundColor(Color.parseColor("#E1516E"))
            3 -> option3.setBackgroundColor(Color.parseColor("#E1516E"))
            4 -> option4.setBackgroundColor(Color.parseColor("#E1516E"))
        }
    }
    fun setQuestion()

    {
       // var dbHelper=Database(this, null)q
        val dbHandler= Database(this, null)

        questionNumber++
        mQuestionList= dbHandler.getQuestions(questionNumber)
        //mQuestionList.add()
        //var Question=mQuestionList!![index - 1]
        val bitmap=utils.getImage(dbHandler.getBitMapByName(index)!!)

        tv_question.text=mQuestionList!!.question
        animal_image.setImageBitmap(bitmap)
        option1.text= mQuestionList!!.optionOne
        option2.text= mQuestionList!!.optionTwo
        option3.text= mQuestionList!!.optionThree
        option4.text=mQuestionList!!.optionFour
        //mCorrectOption=Question.correctAnswer

    }

    override fun onInit(p0: Int){
            //TODO("Not yet implemented")
            if(p0==TextToSpeech.SUCCESS) {
                val result = tts!!.setLanguage(Locale.US)

                if(result==TextToSpeech.LANG_MISSING_DATA||result==TextToSpeech.LANG_NOT_SUPPORTED)
                {
                    Log.e("TTS","The language specified is not supported")
                    //Toast.makeText(this@ExceriseActivity,"Not SUpported",Toast.LENGTH_SHORT).show()
                }
                else {
                    Log.e("TTS","Initialisation Failed")
                    //Toast.makeText(this@ExceriseActivity,"NOt initialised",Toast.LENGTH_SHORT).show()
                }
            }

    }

}