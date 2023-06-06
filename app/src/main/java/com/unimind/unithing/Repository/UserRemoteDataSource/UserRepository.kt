package com.unimind.unithing.Repository.UserRemoteDataSource

import android.graphics.Bitmap


interface UserRepository {
    fun createCertificateDB(callback : (Boolean) -> Unit)
    fun uploadStorage(image: Bitmap, callback : (Boolean) -> Unit)

}