package com.unimind.unithing.Presenter

import android.annotation.SuppressLint
import android.util.Log
import com.google.firebase.Timestamp
import com.unimind.unithing.Contract.PostContract
import com.unimind.unithing.Data.Post
import com.unimind.unithing.Repository.LocalDataSource.UserInfoRepositoryImpl
import com.unimind.unithing.Repository.RemoteDataSource.PostRepositoryImpl
import com.unimind.unithing.RxEventBus
import com.unimind.unithing.RxEvents

/**글을 게시했을 때 로직*/
class PostPresenter(val view: PostContract.View) : PostContract.Presenter {
    var document = mutableListOf<Post>()

    @SuppressLint("CheckResult")
    override fun post(title: String, content: String) {
        val post = Post(
            title = title,
            content = content,
            date = Timestamp.now(),
            history = null,
            nickname = UserInfoRepositoryImpl.currentUser?.nickname.toString(),
            belong = UserInfoRepositoryImpl.currentUser?.belong.toString(),
            postId = makeRandomId(),
        )
        PostRepositoryImpl.post(post) { success ->
            if (success) {
                view.showToast("게시글 업로드 성공")
                view.nextActivity()
            } else view.showToast("게시글 업로드 실패")
        }

//        RxEventBus.listen(RxEvents.PostIdEvent::class.java).subscribe {
//            try {
//
//            } catch (e: Exception) {
//                Log.e("PostIdEvent", "$e")
//            }
//        }


    }

    override fun showPost() {
        PostRepositoryImpl.getAllPost() { result ->
            Log.d("showPost", "$result")
            result.forEach {
                document.add(it.toObject(Post::class.java)!!)
                RxEventBus.publish(RxEvents.PostEvent(true))
            }
        }
    }

    /**랜덤 아이디를 생성해주는 함수*/
    override fun makeRandomId(): String {
        val charset = ('0'..'9') + ('a'..'z') + ('A'..'Z')
        return List(20) { charset.random() }
            .joinToString("")
    }
}