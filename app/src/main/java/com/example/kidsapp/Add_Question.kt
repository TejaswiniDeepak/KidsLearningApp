package com.example.kidsapp

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_add__question.*

class Add_Question : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add__question)
        var Uquestion=et_question.text.toString()
        var Uoption1=et_option1.text.toString()
        var Uoption2=et_option2.text.toString()
        var Uoption3=et_option3.text.toString()
        var Uoption4=et_option4.text.toString()
        var Ucorrectoption=et_corretct_option.text.toString()

        btn_submit_question.setOnClickListener()
        {
            val insert_question=Question(et_question.text.toString(),et_option1.text.toString(),et_option2.text.toString(),et_option3.text.toString(),et_option4.text.toString(),et_corretct_option.text.toString())
            //Log.i("question","$insert_question")
            //Log.i("ques","${et_question.text.toString()}")
            val dbHandler= Database(this,null)
            val addQuestion=dbHandler.addQuestion(insert_question)
            if(addQuestion>0)
            {
                Log.i("database","Data inserted successfully")
            }
            else
            {
                Log.i("database","Not inserted")
            }
        }





    }
}