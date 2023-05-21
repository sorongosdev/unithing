package com.unimind.unithing

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.unimind.unithing.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {
    lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)

        //유효성 검사
        checkEmail()
        checkPassword()

        return binding.root
    }

    //이메일아이디, 비번 유효성 검사 구현
    private fun checkEmail() {
        binding.emailInputEditText.addTextChangedListener {
            val email = binding.emailInputEditText.text.toString()
            val pattern = Patterns.EMAIL_ADDRESS
            binding.emailTextInputLayout.error =
                if (pattern.matcher(email).matches()) null
                else "이메일 주소 형식을 입력해주세요."
        }
    }

    /**비밀번호는 8자리 이상이어야함*/
    private fun checkPassword() {
        binding.passwordInputEditText.addTextChangedListener {
            it?.let { text ->
                binding.passwordTextInputLayout.error = if (text.length < 8) "8자 이상 입력해주세요" else null
            }
        }
    }
}