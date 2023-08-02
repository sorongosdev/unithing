package com.unimind.unithing.Contract

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.unimind.unithing.Data.Post

interface PostContract {
    interface View{
        fun showToast(message: String)
        fun nextActivity()
    }
    interface Presenter{
        fun post(title: String, content: String)
        fun showPost()
        fun makeRandomId(): String
    }
    interface PostRepository{
        fun post(posting : Post, callback: (Boolean) -> Unit)
        fun getAllPost(callback: (MutableList<DocumentSnapshot>) -> Unit)
    }
}