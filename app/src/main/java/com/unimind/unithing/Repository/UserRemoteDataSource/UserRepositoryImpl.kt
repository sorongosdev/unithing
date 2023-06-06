package com.unimind.unithing.Repository.UserRemoteDataSource

import android.graphics.Bitmap
import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.unimind.unithing.CustomApplication
import com.unimind.unithing.Data.MajorCERT
import com.unimind.unithing.R
import com.unimind.unithing.StringResource
import java.io.ByteArrayOutputStream

object UserRepositoryImpl : UserRepository {

    private val firebaseAuth = Firebase.auth

    private val certDBname = StringResource.getStringResource(
        CustomApplication.ctx, R.string.db_major_certificate)

    private val firestoreCertDB = FirebaseFirestore.getInstance().collection(
        certDBname)

    private val userUid = firebaseAuth.uid.toString()

    private val firebaseStorage = Firebase.storage.reference

    override fun createCertificateDB(callback: (Boolean) -> Unit) {
        val majorCERT = MajorCERT(null)

        firestoreCertDB.document(userUid).set(majorCERT)
            .addOnCompleteListener {
                callback(true)
            }.addOnFailureListener {
                callback(false)
            }
    }

    override fun uploadStorage(image: Bitmap, callback: (Boolean) -> Unit) {
        val storageRef = firebaseStorage.child("${certDBname}/${userUid}.jpg")

        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var uploadTask = storageRef.putBytes(data)
        uploadTask.addOnFailureListener {
            callback(false)
        }.addOnSuccessListener { taskSnapshot ->
            callback(true)
        }
    }

}