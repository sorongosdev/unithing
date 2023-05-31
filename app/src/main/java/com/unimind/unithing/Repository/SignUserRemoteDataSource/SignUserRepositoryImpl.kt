package com.unimind.unithing.Repository.SignUserRemoteDataSource

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.unimind.unithing.Data.User

// 싱글톤 객체
object SignUserRepositoryImpl : SingUserRepository {

    private val firebaseAuth = Firebase.auth
    private val firestoreDB = FirebaseFirestore.getInstance().collection("UserAccount")


    override fun requestSignUp(
        email: String,
        password: String,
        callback: (Boolean, String?) -> Unit
    ) {

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    createUserDB(email) { isSuccess ->
                        if (isSuccess) {
                            callback(true, null)
                        } else {
                            deleteUserAuth()
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
        val userUid = firebaseAuth.uid.toString()

        val user = User(email, null, null, null, null, null, null, null)

        firestoreDB.document(userUid).set(user)
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