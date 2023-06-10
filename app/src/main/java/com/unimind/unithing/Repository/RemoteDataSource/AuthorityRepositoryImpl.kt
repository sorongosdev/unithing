package com.unimind.unithing.Repository.RemoteDataSource

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.unimind.unithing.Contract.AuthorityContract
import com.unimind.unithing.CustomApplication
import com.unimind.unithing.R
import com.unimind.unithing.StringResource

object AuthorityRepositoryImpl : AuthorityContract.AuthorityRepository {
    private val firestoreUserDB = FirebaseFirestore.getInstance().collection(
        StringResource.getStringResource(
            CustomApplication.ctx, R.string.db_user_account))
    private lateinit var userUid: String

    override fun getAuthority(callback: (Boolean) -> Unit) {
        userUid = Firebase.auth.uid.toString()
        val docRef = firestoreUserDB.document(userUid)
        docRef.get()
            .addOnSuccessListener { document ->
                val authorized = document.data?.get("authorized")
                Log.d("authorized","${document.id} : $authorized")
                callback(authorized.toString().toBoolean())
            }

    }
}