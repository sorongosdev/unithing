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

    private val firestoreCertDB = FirebaseFirestore.getInstance().collection(
        StringResource.getStringResource(
            CustomApplication.ctx, R.string.db_major_certificate))

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
        val storageRef = firebaseStorage.child("major_certification/${userUid}.jpg")

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


    // 난 왜 이 코드를 짰을까?
//    override fun getUserAuthorized() {
//        var isAuthorized: Boolean
//        firestoreDB.document(getUserUid()).get()
//            .addOnSuccessListener { document ->
//                if (document != null) {
//                    isAuthorized = document.data?.get("authorized") as Boolean
//                    // document에는 문서 스냅샷이 담겨 있음
//                    // ex) DocumentSnapshot data: {badge=null, myPost=null, major=null, level=null, authorized=false, nickname=null, myComment=null, myWishPost=null, experience=null, email=qwer@naver.com}
//                    Log.d("UserRepository", "DocumentSnapshot data: ${document.data}")
//                    Log.d("UserRepository", "authorized : ${document.data?.get("authorized")}")
//                    Log.d("UserRepository", "test : ${isAuthorized}")
//                } else {
//                    Log.d("UserRepository", "No such document")
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.d("UserRepository", "get failed with ", exception)
//            }
//
//
//    }

}