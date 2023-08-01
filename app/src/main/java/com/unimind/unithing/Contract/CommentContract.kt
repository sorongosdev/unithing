package com.unimind.unithing.Contract

import com.unimind.unithing.Data.Post

interface CommentContract {
    interface View {
        /**댓글 보기로 이동*/
        fun showCommentActivity()
        fun updatePostView()
        fun setPostDetailView()
    }

    interface Presenter {
        fun savePostInfo(postInfo: Post)
//        fun initThisPostInfo()
    }

    interface CommentRepository {

    }
}