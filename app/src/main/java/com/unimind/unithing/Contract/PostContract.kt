package com.unimind.unithing.Contract

import com.unimind.unithing.Data.Post

interface PostContract {
    interface View{
        fun showToast(message: String)
        fun nextActivity()
    }
    interface Presenter{
        fun post(title: String, content: String)
    }
    interface PostRepository{
        fun post(posting : Post, callback: (Boolean) -> Unit)
    }
}