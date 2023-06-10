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
import com.unimind.unithing.Presenter.CertificatePresenter
import com.unimind.unithing.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), AuthorityContract.View{
    lateinit var binding: FragmentHomeBinding
    private lateinit var presenter: AuthorityPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        //TODO : authorized가 false면 글쓰기 버튼이 보이지 않게, true면 보이게

//        binding.fragmentHomeFloatingBtn.isVisible =
        presenter = AuthorityPresenter(this)
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
        binding.fragmentHomeFloatingBtn.isVisible = true
    }

    override fun notAuthorized() {
        binding.fragmentHomeFloatingBtn.isVisible = false
    }
}