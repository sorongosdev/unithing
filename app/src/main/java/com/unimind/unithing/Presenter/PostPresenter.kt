package com.unimind.unithing.Presenter

import android.util.Log
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.unimind.unithing.Contract.AuthorityContract
import com.unimind.unithing.Contract.PostContract
import com.unimind.unithing.Contract.UserInfoContract
import com.unimind.unithing.Data.Post
import com.unimind.unithing.Repository.LocalDataSource.UserInfoRepositoryImpl
import com.unimind.unithing.Repository.RemoteDataSource.AuthorityRepositoryImpl
import com.unimind.unithing.Repository.RemoteDataSource.PostRepositoryImpl
import com.unimind.unithing.RxEventBus
import com.unimind.unithing.RxEventBus2
import com.unimind.unithing.RxEvents
import com.unimind.unithing.RxEvents2
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.toObservable
import java.time.LocalDate

/**글을 게시했을 때 로직*/
class PostPresenter(val view: PostContract.View) : PostContract.Presenter {
    var document = mutableListOf<Post>()

    override fun post(title: String, content: String) {
        val post = Post(
            title = title,
            content = content,
            date = Timestamp.now(),
            history = null,
            nickname = UserInfoRepositoryImpl.currentUser?.nickname.toString()
        )
        PostRepositoryImpl.post(post) { success ->
            if (success) {
                view.showToast("게시글 업로드 성공")
                view.nextActivity()
            } else view.showToast("게시글 업로드 실패")
        }
    }

    //TODO : post로 형변환
//    override fun showPost(): MutableList<Post> {
    override fun showPost() {
        PostRepositoryImpl.getAllPost() { result ->
            Log.d("showPost", "$result")
            result.forEach {
                document.add(it.toObject(Post::class.java)!!)
                RxEventBus.publish(RxEvents.EventSetRoom2(true))
            }
        }
//        return document
    }
}