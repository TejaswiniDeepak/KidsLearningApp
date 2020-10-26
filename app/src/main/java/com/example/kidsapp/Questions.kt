package com.example.kidsapp

import android.widget.ImageView

data class Question(
    //val id:Int,
    var question:String,
    val imagePath:String,
    val optionOne:String,
    val optionTwo:String,
    val optionThree:String,
    val optionFour:String,
    var correctAnswer:String
)