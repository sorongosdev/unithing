package com.unimind.unithing

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.unimind.unithing.Contract.CommentContract
import com.unimind.unithing.Data.Post
import com.unimind.unithing.Presenter.CertificatePresenter
import com.unimind.unithing.Presenter.CommentPresenter
import com.unimind.unithing.Presenter.PostPresenter
import com.unimind.unithing.Repository.LocalDataSource.PostInfoRepositoryImpl
import com.unimind.unithing.databinding.ActivityCommentBinding

class CommentActivity : AppCompatActivity(), CommentContract.View {
    private lateinit var binding: ActivityCommentBinding
    private lateinit var commentPresenter: CommentPresenter
    private val itemFeedView = binding.activityCommentFeedDetail
    private lateinit var thisPostInfo: Post

//    private var commentPresenter = CommentPresenter(this)

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_comment)

        commentPresenter = CommentPresenter(this)
        Log.d("Comment", "commentPresenter at CommentActivity is initialized")

        updatePostView()
    }

    override fun showCommentActivity() {
        TODO("Not yet implemented")
    }

    @SuppressLint("CheckResult")
    override fun updatePostView() {
        RxEventBus.listen(RxEvents.CommentEventSetRoom::class.java).subscribe {
            try {
                Log.d("Comment", "subscribe try")
                thisPostInfo = PostInfoRepositoryImpl.postInfo!!
                Log.d("Comment", "thisPostInfo at CommentActivity => $thisPostInfo")
                setPostDetailView()

            } catch (e: Exception) {
                Log.e("CommentEventSetRoom", "$e")
            }
//            val itemFeedView = binding.activityCommentFeedDetail
//            val thisPostInfo = commentPresenter.thisPostInfo
//            Log.d("Comment", "thisPostInfo at CommentActivity => $thisPostInfo")
//
//            itemFeedView.title = thisPostInfo.title
//            itemFeedView.content = thisPostInfo.content
        }
    }

    override fun setPostDetailView() {
        itemFeedView.title = thisPostInfo.title
        itemFeedView.content = thisPostInfo.content
    }
}