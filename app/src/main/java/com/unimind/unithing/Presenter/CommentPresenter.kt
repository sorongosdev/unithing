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
            user_belong = UserInfoRepositoryImpl.currentUser?.belong.toString(),
            content = commentContent,
            comment_id = NicknamePresenter().makeRandomId(),
        )
        CommentRepositoryImpl.registerComment(
            PostInfoRepositoryImpl.postInfo!!.postId, comment
        ) { success ->
            if (success) {
                view.showToast("댓글 등록 성공")
                commentList.clear()
                //TODO : 기존의 댓글에서 등록한 댓글만 추가로 불러오면 좋을것 같음
                showComment()
            } else view.showToast("댓글 등록 실패")
        }
    }

    override fun showComment() {
        CommentRepositoryImpl.getAllComment() { result ->
            result.forEach {
                commentList.add(it.toObject(Comment::class.java)!!)
            }
            CommentRepositoryImpl.allComments = commentList
            Log.d("updateComment","allComments presenter => ${CommentRepositoryImpl.allComments.size}")
            RxEventBus.publish(RxEvents.CommentRegisterEvent(true))
        }
    }
}