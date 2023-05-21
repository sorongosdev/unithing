package com.unimind.unithing

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.unimind.unithing.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {
    lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)

        //회원가입 버튼 누르면 회원가입 페이지로 이동
        binding.signUpButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        //로그인 버튼 누르면 일단은 MainActivity로 이동
        binding.signInButton.setOnClickListener {
            activity?.finish()
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }

}