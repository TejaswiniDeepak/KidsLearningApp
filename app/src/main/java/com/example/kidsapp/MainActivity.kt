package com.example.kidsapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var analytics:FirebaseAnalytics
    @SuppressLint("InvalidAnalyticsName")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_start.setOnClickListener()
        {
            if(et_name.text.toString().isEmpty())
            {
                Toast.makeText(this,"Please Enter the name",Toast.LENGTH_SHORT).show()
                analytics.logEvent("quiz started",null)

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
    }
}