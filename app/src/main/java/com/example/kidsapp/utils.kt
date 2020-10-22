package com.example.kidsapp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresApi
import java.io.ByteArrayOutputStream

object utils {

    fun getBytes(bitmap:Bitmap):ByteArray
    {
        val stream=ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG,0,stream)
        return stream.toByteArray()
    }
    fun getImage(image:ByteArray):Bitmap
        = BitmapFactory.decodeByteArray(image,0,image.size)

}