package com.unimind.unithing.Contract


import android.graphics.Bitmap

interface CertificateContract {

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

    interface CertificateRepository {
        fun createCertificateDB(callback : (Boolean) -> Unit)
        fun uploadStorage(image: Bitmap, callback : (Boolean) -> Unit)
    }
}