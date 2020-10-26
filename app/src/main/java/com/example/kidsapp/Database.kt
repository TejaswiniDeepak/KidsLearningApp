package com.example.kidsapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import androidx.core.database.getBlobOrNull

class Database(context: Context, factory: SQLiteDatabase.CursorFactory?):SQLiteOpenHelper(
    context,
    DATABASE_NAME, null, DATABASE_VERSION
) {
    companion object{
        private const val DATABASE_NAME="Quiz_Question_ImagePath"
        private const val DATABASE_VERSION=2
        private const val TABLE_QUESTION="Questions"
        private const val KEY_ID="id"
        private const val KEY_QUESTION="question"
        private const val KEY_OPTION1="option1"
        private const val KEY_OPTION2="option2"
        private const val KEY_OPTION3="option3"
        private const val KEY_OPTION4="option4"
        private const val KEY_CORRECT_OPTION="answer"
     //   private const val KEY_IMAGE="image"
        private const val KEY_IMAGE_PATH="imagepath"

    }
    override fun onCreate(p0: SQLiteDatabase?) {
        val table=("CREATE TABLE " + TABLE_QUESTION + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_QUESTION + " TEXT, "
                + KEY_IMAGE_PATH + " TEXT, "
                + KEY_OPTION1 + " TEXT, "
                + KEY_OPTION2 + " TEXT, "
                + KEY_OPTION3 + " TEXT, "
                + KEY_OPTION4 + " TEXT, "
                + KEY_CORRECT_OPTION + " TEXT)")


        p0?.execSQL(table)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        val DROP_TABLE="DROP TABLE IF EXISTS $TABLE_QUESTION"
        p0?.execSQL(DROP_TABLE)


        onCreate(p0)
    }
    fun addQuestion(question: Question):Long{
        val db=this.writableDatabase
        val contentValues= ContentValues()
        //contentValues.put(KEY_ID,happyPlace.id)
        contentValues.put(KEY_QUESTION, question.question)
        //contentValues.put(KEY_IMAGE, question.image)
        contentValues.put(KEY_IMAGE_PATH,question.imagePath)
        contentValues.put(KEY_OPTION1, question.optionOne)
        contentValues.put(KEY_OPTION2, question.optionTwo)
        contentValues.put(KEY_OPTION3, question.optionThree)
        contentValues.put(KEY_OPTION4, question.optionFour)
      contentValues.put(KEY_CORRECT_OPTION, question.correctAnswer)
       // contentValues.put(KEY_CORRECT_OPTION,question.correctAnswer)
        val result=db.insert(TABLE_QUESTION, null, contentValues)
        db.close()
        return result
    }
    /**fun getBitMapByName(id: Int):ByteArray?
    {
        val db=this.writableDatabase
        val qb=SQLiteQueryBuilder()
        val sqlSelect= arrayOf(KEY_IMAGE)
        qb.tables= TABLE_QUESTION
        val c=qb.query(
            db, sqlSelect, "id = ?",
            arrayOf(id.toString()), null, null, null
        )
        var result:ByteArray?=null
        if(c.moveToFirst())
        {
            do {

                    result=c.getBlob(c.getColumnIndex(KEY_IMAGE))
            }while (c.moveToNext())
        }
        return result
    }**/
    fun getProfilesCount(): Int {
        val countQuery = "SELECT  * FROM $TABLE_QUESTION"
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery(countQuery, null)
        val count: Int = cursor.getCount()
        cursor.close()
        db.close()

        return count
    }
    fun getQuestions():ArrayList<Question>
    {
        val list=ArrayList<Question>()
        val db=this.readableDatabase
        val cursor=db.rawQuery("SELECT *FROM $TABLE_QUESTION  ", null)

        while(cursor.moveToNext())
        {
            var QuestionFromDatabase:String=cursor.getString(cursor.getColumnIndex(KEY_QUESTION))
            var Option1FromDatabase:String=cursor.getString(cursor.getColumnIndex(KEY_OPTION1))
            var Option2FromDatabase:String=cursor.getString(cursor.getColumnIndex(KEY_OPTION2))
            var Option3FromDatabase:String=cursor.getString(cursor.getColumnIndex(KEY_OPTION3))
           var Option4FromDatabase:String=cursor.getString(cursor.getColumnIndex(KEY_OPTION4))
            var CorrectAnswerFromDatabase:String=cursor.getString(cursor.getColumnIndex(
                KEY_CORRECT_OPTION))
            var ImagePath=cursor.getString(cursor.getColumnIndex(KEY_IMAGE_PATH))
           var  QuestionList:Question=
               Question(QuestionFromDatabase,ImagePath,Option1FromDatabase,
               Option2FromDatabase,Option3FromDatabase,Option4FromDatabase,
            CorrectAnswerFromDatabase)
            list.add(QuestionList)

        }
        cursor.close()
        db.close()
        return list
    }
}