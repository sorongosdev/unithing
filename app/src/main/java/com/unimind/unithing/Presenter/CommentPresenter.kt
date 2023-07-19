package com.unimind.unithing.Presenter

import com.google.firebase.Timestamp
import com.unimind.unithing.Contract.AuthorityContract
import com.unimind.unithing.Contract.CommentContract
import com.unimind.unithing.Data.Post
import com.unimind.unithing.Repository.RemoteDataSource.AuthorityRepositoryImpl

class CommentPresenter(val CommentContractView: CommentContract.View) : CommentContract.Presenter {
    var thisPostInfo = Post(
        nickname = "", //uid가 아닌 닉네임
        title = "",
        content = "",
        date = Timestamp.now(),
        like = 0,
        view = 0,
        comment = 0, //메인에서는 댓글
        history = emptyList(), //이전 수정 내용
    )

    override fun savePostInfo(postInfo: Post) {
        thisPostInfo = postInfo
    }
}