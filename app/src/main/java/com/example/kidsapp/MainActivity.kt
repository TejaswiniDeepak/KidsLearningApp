package com.example.kidsapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var dbHandler=Database(this,null)
        Constants.TOTALQUESTIONS=dbHandler.getProfilesCount()

        Log.i("total questions","${Constants.TOTALQUESTIONS}")


        btn_start.setOnClickListener()
        {
            if(et_name.text.toString().isEmpty())
            {
                Toast.makeText(this,"Please Enter the name",Toast.LENGTH_SHORT).show()


            }
            else if(Constants.TOTALQUESTIONS==0)
            {
                Toast.makeText(this,"please Add Questions before starting quiz",Toast.LENGTH_SHORT).show()
            }
            else
            {

                var intent=Intent(this,QuestionsActivity::class.java)
                Constants.USERNAME=et_name.text.toString()
                //intent.putExtra(Constants.USERNAME,et_name.text.toString())
                Log.i("MainUser","${Constants.USERNAME}")
                startActivity(intent)
                finish()
            }
        }
      btn_add_question.setOnClickListener()
        {
            var intent=Intent(this,Add_Question::class.java)
            startActivity(intent)
            finish()

        }

    }
}