package com.example.kidsapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_questions.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random.Default.nextInt


class QuestionsActivity : AppCompatActivity(),View.OnClickListener, TextToSpeech.OnInitListener{
    private var mQuestionList:ArrayList<Question>?=null
    private var musername: String?=null
    private var index:Int=1
    private var mCorrectOption:Int=1
    private var mSelectedOption:Int=0
    private var QuestionCompleted:Boolean=false
    private var numberOfCorrectAnswers:Int=0
    private var tts:TextToSpeech?=null
    lateinit var shuffledArray:ArrayList<Question>

    override fun onCreate(savedInstanceState: Bundle?) {


        val dbHandler= Database(this, null)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        musername=intent.getStringExtra(Constants.USERNAME)
       mQuestionList= dbHandler.getQuestions()
         shuffledArray=shuffleArray(mQuestionList)
   //Constants.TOTALQUESTIONS=dbHandler.getProfilesCount()
        Log.i("onCreateShuffled","$shuffledArray")
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
                    if (index <= mQuestionList!!.size) {
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
                    if (index == mQuestionList!!.size) {
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

        var dbHelper=Database(this, null)

        var Question= shuffledArray[index-1]
        Log.i("Question1","$Question")
 //shuffledArray?.removeAt(index-1)
        tv_question.text=Question?.question
        Glide.with(this).load(Question?.imagePath).into(animal_image)
        option1.text= Question?.optionOne
        option2.text= Question?.optionTwo
        option3.text= Question?.optionThree
        option4.text= Question?.optionFour
     /**   var alreadyDisplayedQuestions=ArrayList<Int>()
        var randomNumber=nextInt(1,Constants.TOTALQUESTIONS+1)
        var i=0
        alreadyDisplayedQuestions.add(randomNumber)
        Log.i("random number","$randomNumber")
        var arrayListSize=alreadyDisplayedQuestions.size
        for(i in 0 until arrayListSize-1)
        {
            if(randomNumber==alreadyDisplayedQuestions[i]) {
                randomNumber = nextInt(1, Constants.TOTALQUESTIONS + 1)
            }
            else
            {
                var Question=mQuestionList!![randomNumber-1]

                tv_question.text=Question.question
                Glide.with(this).load(Question.imagePath).into(animal_image)
                option1.text= Question.optionOne
                option2.text= Question.optionTwo
                option3.text= Question.optionThree
                option4.text= Question.optionFour
            }**/


        }
        /**var Question=mQuestionList!![randomNumber-1]
        alreadyDisplayedQuestions.add(randomNumber)

            for(i in 0..arrayListSize-1) {
                if (randomNumber == alreadyDisplayedQuestions[i]) {

                }
            }**/
        //val bitmap=utils.getImage(dbHelper.getBitMapByName(index)!!)


        //mCorrectOption=Question.correctAnswer

  //  }

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
    fun shuffleArray(QuestionList:ArrayList<Question>?):ArrayList<Question> {
        val rg: Random = Random()
        Log.i("QyuestionList","$QuestionList")
        for (i in 0..QuestionList!!.size - 1) {
            val randomPosition = rg.nextInt(QuestionList.size)
            val tmp = QuestionList[i]
            QuestionList[i] = QuestionList[randomPosition]
            QuestionList[randomPosition] = tmp
        }

        Log.i("ShuffldList","$QuestionList")
        return QuestionList
    }

}