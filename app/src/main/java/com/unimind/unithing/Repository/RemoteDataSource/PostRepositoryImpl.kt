package com.unimind.unithing.Repository.RemoteDataSource

import android.os.Looper
import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase
import com.unimind.unithing.Contract.PostContract
import com.unimind.unithing.CustomApplication
import com.unimind.unithing.Data.Post
import com.unimind.unithing.R
import com.unimind.unithing.Repository.LocalDataSource.UserInfoRepositoryImpl
import com.unimind.unithing.StringResource

object PostRepositoryImpl : PostContract.PostRepository {

    private lateinit var userUid: String
    lateinit var allPosts: MutableList<Post>

    lateinit var postId: String

    private val firestoreBoardDB = FirebaseFirestore.getInstance().collection(
        StringResource.getStringResource(CustomApplication.ctx, R.string.db_board)
    )
    lateinit var firestorePostDB: CollectionReference

    override fun post(post: Post, callback: (Boolean) -> Unit) {
        userUid = Firebase.auth.uid.toString()

        firestorePostDB.document(post.postId).set(post)
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener {
                callback(false)
            }
    }

    /**db에서 포스트들을 모두 받아옴*/
    override fun getAllPost(callback: (MutableList<DocumentSnapshot>) -> Unit) {
        firestorePostDB = firestoreBoardDB.document(UserInfoRepositoryImpl.currentUser?.major!!)
            .collection("post")

        firestorePostDB
            //최신 데이터가 상단에 보이도록 정렬
            .orderBy("date", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { document ->
                callback(document.documents)
            }
    }

    override fun getNewPost(){

    }

}