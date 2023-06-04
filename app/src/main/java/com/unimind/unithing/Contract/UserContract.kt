package com.unimind.unithing.Contract

import android.graphics.Bitmap

interface UserContract {

    interface View {
        fun showToast(message: String)
        fun nextActivity()
    }

    interface Presenter {
        fun requestCreateDB()
        fun requestUploadImg(image: Bitmap)
    }
}