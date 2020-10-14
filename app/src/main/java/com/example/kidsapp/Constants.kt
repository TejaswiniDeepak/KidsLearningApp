package com.example.kidsapp

object Constants {
   const val USERNAME:String="user_name"
   const val TOTALQUESTIONS:String="total_questions"
   const val CORRECTANSWER:Int=0

   fun getQuestions():ArrayList<Question>
   {
      val QuestionList=ArrayList<Question>()
      var question1=Question(1,"Identify the Animal",R.drawable.tiger,"Lion","cat",
         "Tiger","Dog",3)
      QuestionList.add(question1)
      var question2=Question(2,"Identify the Animal",R.drawable.tiger,"Lion","cat",
         "Tiger","Dog",4)
      QuestionList.add(question2)
      return QuestionList
   }


}