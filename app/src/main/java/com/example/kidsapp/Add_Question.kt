package com.example.kidsapp

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Path
import android.graphics.drawable.BitmapDrawable
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
    companion object{
        private const val IMAGE_DIRECTORY="SelectedImages"
        private var PATH="xyz"
    }

    private var saveImageToInternalStorage:Uri?=null
    var REQUEST_CODE=101
    //var dbHandler=Database(this,null)

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
            if (et_question.text.toString().isEmpty() || image_selected.getDrawable()== null ||
                et_option1.text.toString().isEmpty()||et_option2.text.toString().isEmpty()||et_option3.text.toString().isEmpty()
                ||et_option4.text.toString().isEmpty()||et_corretct_option.text.toString().isEmpty()) {

                Toast.makeText(this, "please fill in all the details", Toast.LENGTH_SHORT).show()
            }
else {
                insertQuestionToDatabase()

                startActivity(Intent(this, MainActivity::class.java))
            }

        }

    }
    fun insertQuestionToDatabase()
    {

        val bitmap=(image_selected.drawable as BitmapDrawable).bitmap
        var imageInBytes=utils.getBytes(bitmap)
        val insert_question=Question(et_question.text.toString(),
            PATH,et_option1.text.toString(),et_option2.text.toString(),
            et_option3.text.toString(),et_option4.text.toString(),et_corretct_option.text.toString())
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
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode==Activity.RESULT_OK && requestCode== REQUEST_CODE) {
            if (data != null) {
                val contentURI = data.data
                image_selected.setImageURI(contentURI)
              try {
                    val selectedImageBitmap =
                        MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI)
                     saveImageToInternalStorage= saveImageToInternalStorage(selectedImageBitmap)
                    Log.e("Saved image","Path::$saveImageToInternalStorage")
                  PATH="$saveImageToInternalStorage"
                  Log.e("Saved image","$PATH")


                    image_selected.setImageBitmap(selectedImageBitmap)
                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(this, "Failed to load image from gallery", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
    private fun saveImageToInternalStorage(bitmap:Bitmap):Uri
    {
        val wrapper=ContextWrapper(applicationContext)
        var file=wrapper.getDir(IMAGE_DIRECTORY,Context.MODE_PRIVATE)
        file= File(file,"${UUID.randomUUID()}.jpg")
        try{
            val stream:OutputStream=FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream)
            stream.flush()
            stream.close()
        }catch(e:IOException)
        {
            e.printStackTrace()
        }
        return Uri.parse(file.absolutePath)
    }


}