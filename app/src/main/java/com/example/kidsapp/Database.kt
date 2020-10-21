package com.example.kidsapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database(context:Context,factory:SQLiteDatabase.CursorFactory?):SQLiteOpenHelper(context,
    DATABASE_NAME,null, DATABASE_VERSION) {
    companion object{
        private const val DATABASE_NAME="updatedQuiz"
        private const val DATABASE_VERSION=1
        private const val TABLE_QUESTION="Questions"
        private const val KEY_ID="id"
        private const val KEY_QUESTION="question"
        private const val KEY_OPTION1="option1"
        private const val KEY_OPTION2="option2"
        private const val KEY_OPTION3="option3"
        private const val KEY_OPTION4="option4"
        private const val KEY_CORRECT_OPTION="answer"

    }
    override fun onCreate(p0: SQLiteDatabase?) {
        val table=("CREATE TABLE " + TABLE_QUESTION + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_QUESTION + " TEXT, "
                + KEY_OPTION1 + " TEXT, "
                + KEY_OPTION2 + " TEXT, "
                + KEY_OPTION3 + " TEXT, "
                + KEY_OPTION4 + " TEXT, "
                + KEY_CORRECT_OPTION + " INTEGER)")


        p0?.execSQL(table)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        val DROP_TABLE="DROP TABLE IF EXISTS $TABLE_QUESTION"
        p0?.execSQL(DROP_TABLE)


        onCreate(p0)
    }
    fun addQuestion(question:Question):Long{
        val db=this.writableDatabase
        val contentValues= ContentValues()
        //contentValues.put(KEY_ID,happyPlace.id)
        contentValues.put(KEY_QUESTION, question.question)
        contentValues.put(KEY_OPTION1,question.optionOne)
        contentValues.put(KEY_OPTION2,question.optionTwo)
        contentValues.put(KEY_OPTION3,question.optionThree)
        contentValues.put(KEY_OPTION4,question.optionFour)
      contentValues.put(KEY_CORRECT_OPTION,question.correctAnswer)
       // contentValues.put(KEY_CORRECT_OPTION,question.correctAnswer)
        val result=db.insert(TABLE_QUESTION, null, contentValues)
        db.close()
        return result
    }
}