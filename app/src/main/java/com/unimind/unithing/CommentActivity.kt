package com.unimind.unithing

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.unimind.unithing.Adapter.CommentNestedAdapter
import com.unimind.unithing.Contract.CommentContract
import com.unimind.unithing.Data.Post
import com.unimind.unithing.Presenter.CommentPresenter
import com.unimind.unithing.Repository.LocalDataSource.PostInfoRepositoryImpl
import com.unimind.unithing.databinding.ActivityCommentBinding


class CommentActivity : AppCompatActivity(), CommentContract.View {
    private lateinit var binding: ActivityCommentBinding
    private lateinit var commentPresenter: CommentPresenter
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
        //댓글뷰 불러오기
        updateCommentView()

        //댓글 등록 버튼 클릭 리스너
        binding.activityCommentCommentBtn.setOnClickListener {
            val commentContent = binding.activityCommentFeedTiet.text.toString()
            commentPresenter.registerComment(commentContent)
            binding.activityCommentFeedTiet.setText("")
            hideKeyboard()
        }
        /************************************************************************/


    }

    @SuppressLint("CheckResult")
    /**전달받은 postInfo로 CommentActivity의 포스트 view를 업데이트*/
    override fun updatePostView() {
        RxEventBus.listen(RxEvents.CommentEvent::class.java).subscribe {
            try {
                thisPostInfo = PostInfoRepositoryImpl.postInfo!!

            } catch (e: Exception) {
                Log.e("CommentEvent", "$e")
            }
            // 5번 실행됨

            (binding.activityCommentRv.adapter as CommentNestedAdapter).setPost(thisPostInfo)
        }
    }

    /**CommentActivity의 댓글 view를 업데이트*/
    @SuppressLint("CheckResult")
    private fun updateCommentView() {
        commentPresenter.showComment()

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

    /**포스트뷰, 댓글뷰가 함께 엮여있는 리사이클러뷰를 초기화해줌*/
    private fun initCommentNestedAdapter() {
        binding.activityCommentRv.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = CommentNestedAdapter()
        }
    }

    override fun showCommentActivity() {
        TODO("Not yet implemented")
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    /**키보드를 내림*/
    override fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
    }
}