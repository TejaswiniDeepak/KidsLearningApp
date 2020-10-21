package com.example.kidsapp

import android.widget.ImageView

data class Question(
    //val id:Int,
    val question:String,
    val image: ImageView,
    val optionOne:String,
    val optionTwo:String,
    val optionThree:String,
    val optionFour:String,
    var correctAnswer:String
)