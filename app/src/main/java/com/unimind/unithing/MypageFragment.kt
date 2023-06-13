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
import com.unimind.unithing.Presenter.AuthorityPresenter
import com.unimind.unithing.databinding.FragmentMypageBinding

class MypageFragment : Fragment(), AuthorityContract.View {
    private lateinit var binding : FragmentMypageBinding
    private lateinit var presenter: AuthorityPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mypage, container, false)
        presenter = AuthorityPresenter(this)
        //생명주기에 따른 코드 이동 필요, 현재 프래그먼트가 등장할 때마다 뷰가 업데이트 되고 있음
        presenter.showAuthority()
        return binding.root
    }

    override fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
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