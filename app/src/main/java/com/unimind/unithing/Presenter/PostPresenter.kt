package com.unimind.unithing.Presenter

import com.google.firebase.Timestamp
import com.unimind.unithing.Contract.AuthorityContract
import com.unimind.unithing.Contract.PostContract
import com.unimind.unithing.Contract.UserInfoContract
import com.unimind.unithing.Data.Post
import com.unimind.unithing.Repository.LocalDataSource.UserInfoRepositoryImpl
import com.unimind.unithing.Repository.RemoteDataSource.AuthorityRepositoryImpl
import com.unimind.unithing.Repository.RemoteDataSource.PostRepositoryImpl
import java.time.LocalDate

/**글을 게시했을 때 로직*/
class PostPresenter(val view: PostContract.View) : PostContract.Presenter{
    override fun post(title: String, content: String) {
        val post = Post(
            title = title,
            content = content,
            date = Timestamp.now(),
            history = null,
            userName = UserInfoRepositoryImpl.currentUser?.nickname.toString()
        )
        PostRepositoryImpl.post(post){success->
            if(success) {
                view.showToast("게시글 업로드 성공")
                view.nextActivity()
            }
            else view.showToast("게시글 업로드 실패")
        }
    }

}