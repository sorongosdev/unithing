package com.unimind.unithing

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.unimind.unithing.Contract.CommentContract
import com.unimind.unithing.Data.Post
import com.unimind.unithing.Presenter.CertificatePresenter
import com.unimind.unithing.Presenter.CommentPresenter
import com.unimind.unithing.databinding.ActivityCommentBinding

class CommentActivity : AppCompatActivity(), CommentContract.View {
    private lateinit var binding: ActivityCommentBinding
    private lateinit var commentPresenter: CommentPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_comment)

        //TODO : rxbus로 presenter가 postInfo를 받아온 다음, 뷰를 업데이트
        updatePostView()
    }

    override fun showCommentActivity() {
        TODO("Not yet implemented")
    }

    override fun updatePostView() {
        val itemFeedView = binding.activityCommentFeedDetail
        val thisPostInfo = commentPresenter.thisPostInfo
        itemFeedView.title = thisPostInfo.title
        itemFeedView.content = thisPostInfo.content
    }
}