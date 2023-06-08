package com.unimind.unithing

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.unimind.unithing.Contract.SignUserContract
import com.unimind.unithing.Presenter.SignUserPresenter
import com.unimind.unithing.databinding.FragmentSigninBinding

class SignInFragment : Fragment(), SignUserContract.View {
    lateinit var binding: FragmentSigninBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signin, container, false)

        val presenter = SignUserPresenter(this)

        //회원가입 버튼 누르면 회원가입 페이지로 이동
        binding.fragmentSigninSignupBtn.setOnClickListener {

            it.findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        //로그인 버튼 누르면 일단은 MainActivity로 이동
        binding.fragmentSigninSigninBtn.setOnClickListener {
            val userEmail = binding.fragmentSigninEmailTiet.text.toString()
            val userPassword = binding.fragmentSigninPwTiet.text.toString()
            presenter.requestSignIn(userEmail, userPassword)
        }
        return binding.root
    }

    override fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun nextActivity() {
        activity?.finish()
        val intent = Intent(activity, CertificationActivity::class.java)
        startActivity(intent)
    }

    override fun showValidation(errorMsg: String?) {
        TODO("Not yet implemented")
    }
}