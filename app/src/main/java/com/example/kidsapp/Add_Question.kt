package com.example.kidsapp

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add__question.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*

class Add_Question : AppCompatActivity() {
    private var saveImageToInternalStorage: Uri?=null
    var REQUEST_CODE=101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add__question)

  btn_add_image.setOnClickListener()
  {
        val intent=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
      intent.type="image/*"
      startActivityForResult(intent,REQUEST_CODE)
  }


        btn_submit_question.setOnClickListener()
        {

            val insert_question=Question(et_question.text.toString(),image_selected,et_option1.text.toString(),et_option2.text.toString(),et_option3.text.toString(),et_option4.text.toString(),et_corretct_option.text.toString())
            //Log.i("question","$insert_question")
            //Log.i("ques","${et_question.text.toString()}")
            val dbHandler= Database(this,null)
            val addQuestion=dbHandler.addQuestion(insert_question)
            if(addQuestion>0)
            {
                Log.i("database","Data inserted successfully")
            }
            else
            {
                Log.i("database","Not inserted")
            }
        }





    }
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode==Activity.RESULT_OK && requestCode== REQUEST_CODE) {
            if (data != null) {
                val contentURI = data.data
                try {
                    val selectedImageBitmap =
                        MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI)
                    // saveImageToInternalStorage= saveImageToInternalStorage(selectedImageBitmap)
                    //Log.e("Saved image","Path::$saveImageToInternalStorage")
                    image_selected.setImageBitmap(selectedImageBitmap)
                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(this, "Failed to load image from gallery", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }


}