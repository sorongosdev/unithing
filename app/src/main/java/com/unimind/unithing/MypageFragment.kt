package com.unimind.unithing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.unimind.unithing.Contract.AuthorityContract
import com.unimind.unithing.Contract.UserInfoContract
import com.unimind.unithing.Presenter.AuthorityPresenter
import com.unimind.unithing.Presenter.UserInfoPresenter
import com.unimind.unithing.databinding.FragmentMypageBinding

class MypageFragment : Fragment(), UserInfoContract.View {
    private lateinit var binding : FragmentMypageBinding
    private lateinit var presenter: UserInfoPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mypage, container, false)

        //presenter
        presenter = UserInfoPresenter(this)
        presenter.getAuthority()
        return binding.root
    }

    override fun showToast(message: String) {
//        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun nextActivity() {
        TODO("Not yet implemented")
    }

    override fun isAuthorized() {
        binding.fragmentMypageAuthorizedIv.isVisible = true
    }

    override fun notAuthorized() {
        binding.fragmentMypageAuthorizedIv.isVisible = false
    }
}