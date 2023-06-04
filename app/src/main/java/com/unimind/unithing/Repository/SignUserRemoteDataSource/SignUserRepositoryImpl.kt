package com.unimind.unithing.Repository.SignUserRemoteDataSource

import android.app.Application
import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.unimind.unithing.CustomApplication
import com.unimind.unithing.Data.User
import com.unimind.unithing.R
import com.unimind.unithing.StringResource

// 싱글톤 객체
object SignUserRepositoryImpl : SignUserRepository {

    private val firebaseAuth = Firebase.auth

    private val firestoreUserDB = FirebaseFirestore.getInstance().collection(StringResource.getStringResource(CustomApplication.ctx, R.string.db_user_account))
    private val firestoreCertDB = FirebaseFirestore.getInstance().collection(StringResource.getStringResource(CustomApplication.ctx, R.string.db_major_certificate))
    //private val userUid = firebaseAuth.uid.toString()
    private lateinit var userUid: String

    override fun requestSignUp(
        email: String,
        password: String,
        callback: (Boolean, String?) -> Unit
    ) {

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    userUid = task.result.user?.uid.toString()
                    createUserDB(email) { isSuccess ->
                        if (isSuccess) {
                            callback(true, null)
                        } else {
                            deleteUserAuth()
                            callback(false, "회원가입 실패")
                        }
                    }
                } else {
                    val errorMessage = task.exception?.message ?: "회원가입 실패"
                    callback(false, errorMessage)
                }
            }
    }

    override fun requestSignIn(
        email: String,
        password: String,
        callback: (Boolean, String?) -> Unit
    ) {

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, null)
                } else {
                    val errorMessage = task.exception?.message ?: "로그인 실패 "
                    callback(false, errorMessage)
                }
            }
    }


    private fun createUserDB(email: String, callback: (Boolean) -> Unit) {
        val user = User(email = email, uid = userUid, authorized = false)

        firestoreUserDB.document(userUid).set(user)
            .addOnCompleteListener {
                callback(true)
            }.addOnFailureListener {
                callback(false)
            }

    }

    private fun deleteUserAuth() {
        // 향후 onComplete 및 onFailure 리스너 구현해야 할 가능성?
        firebaseAuth.currentUser?.delete()
    }
}