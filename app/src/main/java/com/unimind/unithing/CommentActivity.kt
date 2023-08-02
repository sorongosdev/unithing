package com.unimind.unithing

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.unimind.unithing.Adapter.CommentAdapter
import com.unimind.unithing.Adapter.CommentNestedAdapter
import com.unimind.unithing.Adapter.HomeAdapter
import com.unimind.unithing.Contract.CommentContract
import com.unimind.unithing.Data.Post
import com.unimind.unithing.Presenter.CertificatePresenter
import com.unimind.unithing.Presenter.CommentPresenter
import com.unimind.unithing.Presenter.PostPresenter
import com.unimind.unithing.Repository.LocalDataSource.PostInfoRepositoryImpl
import com.unimind.unithing.Repository.LocalDataSource.UserInfoRepositoryImpl
import com.unimind.unithing.databinding.ActivityCommentBinding
import com.unimind.unithing.databinding.ItemFeedBinding

class CommentActivity : AppCompatActivity(), CommentContract.View {
    private lateinit var binding: ActivityCommentBinding
    private lateinit var commentPresenter: CommentPresenter
    lateinit var itemFeedView: ItemFeedBinding
    private lateinit var thisPostInfo: Post

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_comment)

        commentPresenter = CommentPresenter(this)

        //초기화
        initCommentNestedAdapter()

        /***************포스트 관련*/

        //포스트 뷰 불러오기
        updatePostView()

        /************************/

        /****************************************************************댓글 관련*/

        //댓글 어댑터 초기화
//        initCommentRv()

        //댓글 불러오기
        commentPresenter.showComment()
        updateCommentView()

        //댓글 등록 버튼 클릭 리스너*/
        binding.activityCommentCommentBtn.setOnClickListener {
            val commentContent = binding.activityCommentFeedTiet.text.toString()
            commentPresenter.registerComment(commentContent)
        }

        /************************************************************************/


    }
    @SuppressLint("CheckResult")
    /**전달받은 postInfo로 CommentActivity의 view를 업데이트 한다.*/
    override fun updatePostView() {
        RxEventBus.listen(RxEvents.CommentEvent::class.java).subscribe {
            try {
                thisPostInfo = PostInfoRepositoryImpl.postInfo!!
                Log.d("Adapter","thisPostInfo => $thisPostInfo")
                //10번이나 실행됨
                (binding.activityCommentRv.adapter as CommentNestedAdapter).setPost(thisPostInfo)

            } catch (e: Exception) {
                Log.e("CommentEvent", "$e")
            }
        }
    }

    @SuppressLint("CheckResult")
    private fun updateCommentView() {
        RxEventBus.listen(RxEvents.CommentRegisterEvent::class.java).subscribe {
            try {
                (binding.activityCommentRv.adapter as CommentNestedAdapter).setComment(
                    commentPresenter.commentList
                )
            } catch (e: Exception) {
                Log.e("CommentRegisterEvent", "$e")
            }
        }
    }

    private fun initCommentNestedAdapter() {
        binding.activityCommentRv.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = CommentNestedAdapter()
        }
    }

    override fun showCommentActivity() {
        TODO("Not yet implemented")
    }



//    override fun setPostDetailView() {
//        itemFeedView = binding.activityCommentFeedDetail
//
//        itemFeedView.title = thisPostInfo.title
//        itemFeedView.content = thisPostInfo.content
//        itemFeedView.nickname = thisPostInfo.nickname
//        itemFeedView.belong = UserInfoRepositoryImpl.currentUser?.major
//    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    /**댓글 리사이클러뷰를 초기화해주는 함수*/
    private fun initCommentRv() {
        binding.activityCommentRv.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = CommentAdapter(mutableListOf())
        }
    }
}