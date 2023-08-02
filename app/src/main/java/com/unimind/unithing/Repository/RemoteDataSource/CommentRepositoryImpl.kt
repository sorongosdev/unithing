package com.unimind.unithing.Repository.RemoteDataSource

import android.os.Looper
import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.unimind.unithing.Contract.CommentContract
import com.unimind.unithing.Contract.PostContract
import com.unimind.unithing.CustomApplication
import com.unimind.unithing.Data.Comment
import com.unimind.unithing.Data.Post
import com.unimind.unithing.Data.User
import com.unimind.unithing.R
import com.unimind.unithing.Repository.LocalDataSource.PostInfoRepositoryImpl
import com.unimind.unithing.Repository.LocalDataSource.UserInfoRepositoryImpl
import com.unimind.unithing.RxEventBus
import com.unimind.unithing.RxEvents
import com.unimind.unithing.StringResource
import java.util.logging.Handler

object CommentRepositoryImpl : CommentContract.CommentRepository {
    private val firestoreBoardDB =
        FirebaseFirestore.getInstance().collection(
            StringResource.getStringResource(CustomApplication.ctx, R.string.db_board)
        )
    private val firestorePostDB =
        firestoreBoardDB.document(UserInfoRepositoryImpl.currentUser?.major!!).collection("post")

    override fun registerComment(postId: String, comment: Comment, callback: (Boolean) -> Unit) {
        firestorePostDB.document(postId).collection("comment")
            .document(comment.comment_id)
            .set(comment)
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener {
                callback(false)
            }
    }
    override fun getAllComment(callback: (MutableList<DocumentSnapshot>) -> Unit) {
        firestorePostDB.document(PostInfoRepositoryImpl.postInfo!!.postId).collection("comment")
            .get()
            .addOnSuccessListener { document ->
                callback(document.documents)
            }

    }

}