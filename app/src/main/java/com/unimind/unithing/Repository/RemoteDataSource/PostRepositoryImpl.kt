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
import com.unimind.unithing.Contract.PostContract
import com.unimind.unithing.CustomApplication
import com.unimind.unithing.Data.Post
import com.unimind.unithing.Data.User
import com.unimind.unithing.R
import com.unimind.unithing.Repository.LocalDataSource.UserInfoRepositoryImpl
import com.unimind.unithing.RxEventBus
import com.unimind.unithing.RxEvents
import com.unimind.unithing.StringResource
import java.util.logging.Handler

object PostRepositoryImpl : PostContract.PostRepository {

    private lateinit var userUid: String
    private lateinit var allPosts: DocumentSnapshot
    lateinit var postId: String

    private val firestoreBoardDB = FirebaseFirestore.getInstance().collection(
        StringResource.getStringResource(CustomApplication.ctx, R.string.db_board)
    )
    lateinit var firestorePostDB: CollectionReference

    override fun post(post: Post, callback: (Boolean) -> Unit) {
        userUid = Firebase.auth.uid.toString()

        firestorePostDB.document(post.postId).set(post)
            .addOnSuccessListener {
                Log.d("post", "Success")

                callback(true)
                RxEventBus.publish(RxEvents.PostIdEventSetRoom(true))
            }
            .addOnFailureListener {
                Log.d("post", "Fail")

                callback(false)
            }
    }

    /**db에서 포스트들을 모두 받아옴*/
    override fun getAllPost(callback: (MutableList<DocumentSnapshot>) -> Unit) {
        // major가 null이면 null 반환. 현재 ghkdud4@naver.com 의 major 필드값은 null이니 type으로 테스트

        firestorePostDB = firestoreBoardDB.document(UserInfoRepositoryImpl.currentUser?.major!!)
            .collection("post")

        firestorePostDB
            //최신 데이터가 상단에 보이도록 정렬
            .orderBy("date", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { document ->
                Log.d("getAllPost", "${document.documents}")
                callback(document.documents)
            }
    }

}