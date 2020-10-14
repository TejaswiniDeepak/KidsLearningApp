package com.example.kidsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        congrulations.text="Congrulations ${Constants.USERNAME}"
        result.text="${Constants.CORRECTANSWER}/${Constants.TOTALQUESTIONS}"
        Log.i("name","${Constants.USERNAME}")
        Log.i("name","${Constants.CORRECTANSWER}")
        Log.i("name","${Constants.TOTALQUESTIONS}")


      btn_start_again.setOnClickListener{
            var intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}