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
import com.google.firebase.firestore.DocumentSnapshot
import com.unimind.unithing.Contract.AuthorityContract
import com.unimind.unithing.Contract.PostContract
import com.unimind.unithing.Contract.UserInfoContract
import com.unimind.unithing.Data.Post
import com.unimind.unithing.Presenter.AuthorityPresenter
import com.unimind.unithing.Presenter.PostPresenter
import com.unimind.unithing.Presenter.SignUserPresenter
import com.unimind.unithing.Presenter.UserInfoPresenter
import com.unimind.unithing.databinding.FragmentHomeBinding
import org.w3c.dom.Document

class HomeFragment : Fragment(), UserInfoContract.View, PostContract.View {
    lateinit var binding: FragmentHomeBinding

    //    private lateinit var presenter: AuthorityPresenter
    private lateinit var userInfoPresenter: UserInfoPresenter
    private lateinit var postPresenter: PostPresenter
//    private lateinit var observer: Observer
//    private lateinit var posts: MutableList<Post>

    @SuppressLint("CheckResult")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        //TODO : authorized가 false면 글쓰기 버튼이 보이지 않게, true면 보이게

        //presenter
        Log.d("homeFragment", "homeFragment")
        userInfoPresenter = UserInfoPresenter(this)
        postPresenter = PostPresenter(this)
        Log.d("homeFragment", "presenter")

        initHomeRv()

        RxEventBus.listen(RxEvents.EventSetRoom::class.java).subscribe {
            postPresenter.showPost()
        }

        //
        RxEventBus.listen(RxEvents.EventSetRoom2::class.java).subscribe {
            (binding.fragmentHomeRv.adapter as HomeAdapter).setData(postPresenter.document)
        }

        binding.fragmentHomeFloatingBtn.setOnClickListener {
            nextActivity()
        }

        return binding.root
    }

    private fun initHomeRv() {
        Log.d("initHomeRv","init")
        binding.fragmentHomeRv.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = HomeAdapter(mutableListOf())
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
}