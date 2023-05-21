package com.unimind.unithing.Repository.SignUserRemoteDataSource

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

// 싱글톤 객체
object SignUserRepositoryImpl : SingUserRepository {

    private val firebaseAuth = Firebase.auth


    override fun requestSignUp(
        email: String,
        password: String,
        callback: (Boolean, String?) -> Unit
    ) {

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, null)
                } else {
                    val errorMessage = task.exception?.message ?: "Sign up failed."
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
                    val errorMessage = task.exception?.message ?: "Sign in failed."
                    callback(false, errorMessage)
                }
            } }
}