package com.unimind.unithing

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.unimind.unithing.Adapter.HomeAdapter
import com.unimind.unithing.Contract.CommentContract
import com.unimind.unithing.Contract.PostContract
import com.unimind.unithing.Contract.UserInfoContract
import com.unimind.unithing.Presenter.PostPresenter
import com.unimind.unithing.Presenter.UserInfoPresenter
import com.unimind.unithing.databinding.FragmentHomeBinding
import io.reactivex.Observable

class HomeFragment : Fragment(), UserInfoContract.View, PostContract.View, CommentContract.View {
    lateinit var binding: FragmentHomeBinding

    private lateinit var userInfoPresenter: UserInfoPresenter
    private lateinit var postPresenter: PostPresenter
    private val listener = this

    @SuppressLint("CheckResult")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        //TODO : authorized가 false면 글쓰기 버튼이 보이지 않게, true면 보이게

        //presenter
        userInfoPresenter = UserInfoPresenter(this)
        postPresenter = PostPresenter(this)

        initHomeRv()

        //리스트 업데이트
        RxEventBus.listen(RxEvents.CurrentUserEventSetRoom::class.java).subscribe {
            try{
                postPresenter.showPost()
                Log.d("CurrentUserEventSetRoom","success")
            } catch(e: Exception){
                Log.e("CurrentUserEventSetRoom","$e")
            }
        }

        RxEventBus.listen(RxEvents.PostEventSetRoom::class.java).subscribe {
            try{
                (binding.fragmentHomeRv.adapter as HomeAdapter).setData(postPresenter.document)
                Log.d("PostEventSetRoom","success")
            } catch(e: Exception){
                Log.e("PostEventSetRoom","$e")
            }
        }

        //글쓰기 버튼
        binding.fragmentHomeFloatingBtn.setOnClickListener {
            nextActivity()
        }

        return binding.root
    }

    private fun initHomeRv() {
        Log.d("initHomeRv", "init")
        binding.fragmentHomeRv.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = HomeAdapter(mutableListOf(), listener)
        }
    }

    override fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun nextActivity() {
//        activity?.finish()
        val intent = Intent(activity, PostActivity::class.java)
        startActivity(intent)
    }

    override fun isAuthorized() {
        binding.fragmentHomeFloatingBtn.isVisible = true
    }

    override fun notAuthorized() {
        binding.fragmentHomeFloatingBtn.isVisible = false
    }

    override fun showCommentActivity() {
        val intent = Intent(activity, CommentActivity::class.java)
        startActivity(intent)
    }

    override fun updatePostView() {
        TODO("Not yet implemented")
    }

}