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
      var question3=Question(3,"Identify the Animal",R.drawable.lion,"Lion","cat",
         "Tiger","Dog",1)
      QuestionList.add(question3)
      var question4=Question(4,"Identify the Animal",R.drawable.cat,"Lion","cat",
         "Tiger","Dog",2)
      QuestionList.add(question4)
      var question5=Question(5,"Identify the Animal",R.drawable.cow,"Cow","cat",
         "Tiger","Dog",1)
      QuestionList.add(question5)
      var question6=Question(6,"Identify the Animal",R.drawable.elephant,"Lion","cat",
         "Tiger","Elephant",4)
      QuestionList.add(question6)
      var question7=Question(7,"Identify the Animal",R.drawable.goat,"Goat","rat",
         "donkey","Rabbit",1)
      QuestionList.add(question7)
      var question8=Question(8,"Identify the Animal",R.drawable.horse,"Goat","horse",
         "monkey","Rabbit",2)
      QuestionList.add(question8)
      var question9=Question(9,"Identify the Animal",R.drawable.rabbit,"spider","rat",
         "donkey","Rabbit",4)
      QuestionList.add(question9)
      var question10=Question(10,"Identify the Animal",R.drawable.monkey,"Goat","monkey",
         "donkey","Rabbit",2)
      QuestionList.add(question10)

      return QuestionList
   }


}