package com.unimind.unithing.Repository.RemoteDataSource

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.unimind.unithing.Contract.PostContract
import com.unimind.unithing.CustomApplication
import com.unimind.unithing.Data.Post
import com.unimind.unithing.R
import com.unimind.unithing.Repository.LocalDataSource.UserInfoRepositoryImpl
import com.unimind.unithing.StringResource

object PostRepositoryImpl : PostContract.PostRepository {

    private val firebaseAuth = Firebase.auth
    private lateinit var userUid: String

    private val firestoreBoardDB = FirebaseFirestore.getInstance().collection(
        StringResource.getStringResource(
            CustomApplication.ctx,
            R.string.db_board
        )
    )
    override fun post(post : Post,callback: (Boolean)->Unit) {
        userUid = Firebase.auth.uid.toString()
        firestoreBoardDB.document(UserInfoRepositoryImpl.currentUser?.major!!).collection("posts").add(post)
            .addOnSuccessListener {
                Log.d("post","Success")
                callback(true)
            }
            .addOnFailureListener {
                callback(false)
            }
    }

}