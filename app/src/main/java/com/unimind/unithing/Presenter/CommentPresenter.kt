package com.unimind.unithing.Presenter

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.google.firebase.Timestamp
import com.unimind.unithing.Contract.CommentContract
import com.unimind.unithing.Data.Comment
import com.unimind.unithing.Data.Post
import com.unimind.unithing.Repository.LocalDataSource.PostInfoRepositoryImpl
import com.unimind.unithing.Repository.LocalDataSource.UserInfoRepositoryImpl
import com.unimind.unithing.Repository.RemoteDataSource.CommentRepositoryImpl
import com.unimind.unithing.Repository.RemoteDataSource.PostRepositoryImpl
import com.unimind.unithing.RxEventBus
import com.unimind.unithing.RxEvents

class CommentPresenter(val view: CommentContract.View) : CommentContract.Presenter {
    var commentList = mutableListOf<Comment>()

    override fun savePostInfo(postInfo: Post) {

        PostInfoRepositoryImpl.postInfo = postInfo
        RxEventBus.publish(RxEvents.CommentEvent(true))
    }

    override fun registerComment(commentContent: String) {
        val comment = Comment(
            user_nickname = UserInfoRepositoryImpl.currentUser?.nickname.toString(), //uid가 아닌 닉네임
            user_belong = UserInfoRepositoryImpl.currentUser?.major.toString(),
            content = commentContent,
            comment_id = makeRandomId(),
        )
        CommentRepositoryImpl.registerComment(
            PostInfoRepositoryImpl.postInfo!!.postId, comment
        ) { success ->
            if (success) {
                view.showToast("댓글 등록 성공")
            } else view.showToast("댓글 등록 실패")
        }
    }

    override fun showComment() {
        CommentRepositoryImpl.getAllComment() { result ->
            result.forEach {
                commentList.add(it.toObject(Comment::class.java)!!)
            }
            RxEventBus.publish(RxEvents.CommentRegisterEvent(true))
        }
    }

    override fun makeRandomId(): String {
        val charset = ('0'..'9') + ('a'..'z') + ('A'..'Z')
        return List(20) { charset.random() }
            .joinToString("")
    }
}