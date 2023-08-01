package com.unimind.unithing.Presenter

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.google.firebase.Timestamp
import com.unimind.unithing.Contract.CommentContract
import com.unimind.unithing.Data.Post
import com.unimind.unithing.Repository.LocalDataSource.PostInfoRepositoryImpl
import com.unimind.unithing.RxEventBus
import com.unimind.unithing.RxEvents

class CommentPresenter(val view: CommentContract.View) : CommentContract.Presenter {
    override fun savePostInfo(postInfo: Post) {
        PostInfoRepositoryImpl.postInfo = postInfo
        Log.d("Comment", "thisPostInfo at CommentPresenter => $postInfo")
        RxEventBus.publish(RxEvents.CommentEventSetRoom(true))

        Log.d("Comment", "CommentPresenter published")
        Log.d("Comment", "published, thisPostInfo => $postInfo")
    }

//    override fun initThisPostInfo() {
//        Log.d("CommentPresenter","initThisPostInfo")
//
//        thisPostInfo = Post(
//            nickname = "", //uid가 아닌 닉네임
//            title = "",
//            content = "",
//            date = Timestamp.now(),
//            like = 0,
//            view = 0,
//            comment = 0, //메인에서는 댓글
//            history = emptyList(), //이전 수정 내용
//        )
//    }
}