package com.unimind.unithing.Contract

import com.google.firebase.firestore.DocumentSnapshot
import com.unimind.unithing.Data.Comment
import com.unimind.unithing.Data.Post

interface CommentContract {
    interface View {
        /**댓글 보기로 이동*/
        fun showCommentActivity()
        fun updatePostView()
//        fun setPostDetailView()
        fun showToast(message: String)
        fun hideKeyboard()
    }

    interface Presenter {
        fun savePostInfo(postInfo: Post)
        fun registerComment(commentContent: String)
        fun showComment()
    }

    interface CommentRepository {
        fun registerComment(postId: String, comment: Comment, callback: (Boolean) -> Unit)
        fun getAllComment(callback: (MutableList<DocumentSnapshot>) -> Unit)
    }
}