package com.example.kidsapp

object Constants {
var USERNAME:String="user_name"
   var TOTALQUESTIONS:String="total_questions"
   var CORRECTANSWER:String="0"

   fun getQuestions():ArrayList<Question>
   {
      val QuestionList=ArrayList<Question>()
      var question1=Question(1,"Identify the Animal",R.drawable.tiger,"Lion","cat",
         "Tiger","Dog",3)
      QuestionList.add(question1)
      var question2=Question(2,"Identify the Animal",R.drawable.doggy,"Lion","cat",
         "Tiger","Dog",4)
      QuestionList.add(question2)
      return QuestionList
   }


}