package com.unimind.unithing.Contract


import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap

interface UserContract {

    interface View {
        fun showToast(message: String)
        fun nextActivity()
        fun setImageView(bitmap: Bitmap)
        fun setText(text: String)
        fun openCamera()
        fun openGallery()
    }

    interface Presenter {
        fun requestCreateDB()
        fun requestUploadImg(image: Bitmap)
        fun recognizeFromImage(img: Bitmap)
        fun requestPermissions(type: String)
    }
}